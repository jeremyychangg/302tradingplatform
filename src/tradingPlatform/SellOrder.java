// 	******************************************************************************************
// 	**																						**
// 	**	Filename: SellOrder.java 															**
// 	**																						**
// 	**	Description: Subclass of order which creates a sell order							**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 20/05/2021															**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************


package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;
import tradingPlatform.exceptions.InsufficientInventoryException;
import tradingPlatform.exceptions.InvalidAssetException;
import tradingPlatform.exceptions.InvalidOrderException;
import tradingPlatform.exceptions.NegativePriceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 *
 */
public class SellOrder extends Order {
    public SellOrder(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
                     OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        super(orderID, userID, unitID, assetID, orderTime, orderStatus,
                orderType, orderPrice, orderQuant, quantFilled, quantRemain);
    }

    /**
     *
     * @param userID
     * @param assetID
     * @param orderPrice
     * @param orderQuant
     * @throws SQLException
     * @throws InvalidAssetException
     * @throws InvalidOrderException
     * @throws NegativePriceException
     * @throws InsufficientInventoryException
     */
    public SellOrder(String userID, String assetID, double orderPrice, int orderQuant)
            throws SQLException, InvalidAssetException, InvalidOrderException, NegativePriceException, InsufficientInventoryException {

        super(userID, assetID, OrderType.SELL, orderPrice, orderQuant);

        // Check if asset in inventory
        Inventory currentInventory = new Inventory(this.unitID);
        if (!(currentInventory.AssetInInventory(this.assetID))) {
            throw new InvalidAssetException("Order Error: Order asset not in inventory.");
        }

        // Check enough asset quantity to sell
        if (!(currentInventory.SufficientAssetQuantity(this.assetID, this.orderQuant))) {
            throw new InsufficientInventoryException("Order Error: Insufficient asset quantity in inventory");
        }

        AddOrderDatabase();

        ExecuteSellOrder();
    }

    /**
     *
     * @throws SQLException
     * @throws InvalidOrderException
     * @throws InvalidAssetException
     * @throws NegativePriceException
     */
    public void ExecuteSellOrder() throws SQLException, InvalidOrderException, InvalidAssetException, NegativePriceException {
        // Current asset instance
        Asset currentAsset = Asset.findAsset(this.assetID);

        // Check if just ordered asset has corresponding order

        // Find buy orders of same asset
        Statement smt = connection.createStatement();
        String sqlFindOrder
                = "SELECT * FROM orders WHERE assetID = '" + assetID + "' and orderType = 'BUY' " +
                "and orderStatus = 'INCOMPLETE' and unitID != '" + this.unitID + "' " +
                "ORDER BY orderTime;";
        ResultSet buyOrders = smt.executeQuery(sqlFindOrder);

        // List to store corresponding buy orders of the same asset
        ArrayList<Order> matchingOrders = new ArrayList<>();

        // Add queried rows as new Buy Orders in list
        while (buyOrders.next()) {
            matchingOrders.add(
                    new BuyOrder(
                            buyOrders.getString("orderID"),
                            buyOrders.getString("userID"),
                            buyOrders.getString("unitID"),
                            buyOrders.getString("assetID"),
                            buyOrders.getString("orderTime").substring(0,19),
                            OrderStatus.valueOf(buyOrders.getString("orderStatus")),
                            OrderType.valueOf(buyOrders.getString("orderType")),
                            buyOrders.getDouble("orderPrice"),
                            buyOrders.getInt("orderQuantity"),
                            buyOrders.getInt("quantFilled"),
                            buyOrders.getInt("quantRemain")
                    )
            );
        }

        // Remove orders if the buy bid is less than the sell ask
        matchingOrders.removeIf(buys -> (buys.orderPrice < this.orderPrice));

        // List to store required orders that can fully/partially or completely not fill order
        ArrayList<Order> requiredOrders = new ArrayList<>();

        // Required asset amount to facilitate quantity of this order
        int requiredQuant = this.quantRemain;

        // Loop through orders matching conditions and append as many orders possible to
        // fully/partially facilitate this order
        for (int i = 0; i < matchingOrders.size(); i++) {
            // Stores quantity of current order in matchingOrders list
            int quantity = matchingOrders.get(i).quantRemain;
            if (requiredQuant > 0) {
                requiredOrders.add(matchingOrders.get(i));
                requiredQuant -= quantity;
            } else if (requiredQuant <= 0) {
                break;
            }
        }

        // If requiredOrders is filled it can either
        //      - Have enough buy orders to fully facilitate the sell order
        //          * Complete sell order (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Update buyer and seller inventory numbers
        //          * Change buy order (INCOMPLETE, quantFilled, quantRemain)
        //      - Have enough buy orders to partially fill the sell order
        //          * Complete the corresponding buy order/s (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Change sell order (INCOMPLETE, change quant remain, change quant filled)
        //          * Add inventory entry and update corresponding inventory numbers

        for (int i = 0; i < requiredOrders.size(); i++) {
            // How much this order has remaining to fill
            int sellQuant = this.quantRemain;
            // How much can this corresponding order faciliate of this order
            int buyQuant = requiredOrders.get(i).quantRemain;

            int executed = 0;

            if (buyQuant > sellQuant) {
                // When this order has more than the quantity required to fill remaining units

                // Stores how many more units the buy order has over this sell order
                this.quantFilled += sellQuant;
                this.quantRemain -= sellQuant;

                // Deduct the amount remaining of the sell order from the quantity remaining in the buy order
                requiredOrders.get(i).quantRemain -= sellQuant;
                requiredOrders.get(i).quantFilled += sellQuant;
                executed = sellQuant;

                // Change quantities in database
                ChangeQuantRemainFilled(this.quantRemain, this.quantFilled);
                requiredOrders.get(i).ChangeQuantRemainFilled(requiredOrders.get(i).quantRemain, requiredOrders.get(i).quantFilled);

                // Set complete status of this order
                this.ChangeStatus(OrderStatus.COMPLETE);

            } else if (buyQuant == sellQuant) {
                // When this order has exactly the same amount required to fill remaining units

                // Stores how many more units the buy order has over this sell order
                this.quantFilled += sellQuant;
                this.quantRemain -= sellQuant;

                // Deduct the amount remaining of the sell order from the quantity remaining in the buy order
                requiredOrders.get(i).quantRemain -= sellQuant;
                requiredOrders.get(i).quantFilled += sellQuant;
                executed = sellQuant;

                assert requiredOrders.get(i).quantRemain == 0;
                assert requiredOrders.get(i).quantFilled == requiredOrders.get(i).orderQuant;

                // Change quantities in database
                this.ChangeQuantRemainFilled(this.quantRemain, this.quantFilled);
                requiredOrders.get(i).ChangeQuantRemainFilled(requiredOrders.get(i).quantRemain, requiredOrders.get(i).quantFilled);

                // Set complete status of this order
                this.ChangeStatus(OrderStatus.COMPLETE);
                requiredOrders.get(i).ChangeStatus(OrderStatus.COMPLETE);

            } else {
                // When this order has less than required amount to fill remaining units

                // Stores how many more units the buy order has over this sell order
                this.quantFilled += requiredOrders.get(i).quantRemain;
                this.quantRemain -= requiredOrders.get(i).quantRemain;

                assert this.quantRemain + this.quantFilled == this.orderQuant;

                // Deduct the amount remaining of the sell order from the quantity remaining in the buy order
                requiredOrders.get(i).quantRemain -= buyQuant;
                requiredOrders.get(i).quantFilled += buyQuant;
                executed = buyQuant;

                assert requiredOrders.get(i).quantRemain == 0;
                assert requiredOrders.get(i).quantFilled == requiredOrders.get(i).orderQuant;

                // Change quantities in database
                ChangeQuantRemainFilled(this.quantRemain, this.quantFilled);
                requiredOrders.get(i).ChangeQuantRemainFilled(requiredOrders.get(i).quantRemain, requiredOrders.get(i).quantFilled);

                // Set complete status of this order
                ChangeStatus(OrderStatus.INCOMPLETE);
                requiredOrders.get(i).ChangeStatus(OrderStatus.COMPLETE);

            }

            // Change asset price
            currentAsset.SetPrice(requiredOrders.get(i).orderPrice);

            // Change inventory amounts
            new InventoryItem(this.unitID, this.assetID, requiredOrders.get(i).orderPrice, -executed, this.orderID);
            new InventoryItem(requiredOrders.get(i).unitID, requiredOrders.get(i).assetID, requiredOrders.get(i).orderPrice, executed, requiredOrders.get(i).orderID);

            // Set credit balance of units
            // Unit.ChangeUnitBalance(this.unitID, this.orderPrice * executed);
            // Unit.ChangeUnitBalance(requiredOrders.get(i).unitID, -this.orderPrice * executed);
        }

        // If requiredOrders is empty, no current buy orders are able to facilitate sell order

        // Modify watchlist balances

    }
}