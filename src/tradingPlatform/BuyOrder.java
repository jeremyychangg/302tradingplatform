// 	******************************************************************************************
// 	**																						**
// 	**	Filename: BuyOrder.java 															**
// 	**																						**
// 	**	Description: Subclass of order which creates a buy order							**
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
import tradingPlatform.exceptions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 * Buy Order class to facilitate different types of buy orders. Inherits order class.
 */
public class BuyOrder extends Order {
    /**
     * Buy Order instance with all fields. This constructor does not create the instance in the database as it already exists.
     * It will call the super class's constructor to do so.
     * @param orderID orderID
     * @param userID userID
     * @param unitID unitID
     * @param assetID assetID
     * @param orderTime Time of order
     * @param orderStatus Completed or Incomplete
     * @param orderType Buy or Sell
     * @param orderPrice Price of order
     * @param orderQuant Quantity of order
     * @param quantFilled Quantity of order filled
     * @param quantRemain Quantity of order remaining
     */
    public BuyOrder(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
                    OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        super(orderID, userID, unitID, assetID, orderTime, orderStatus,
                orderType, orderPrice, orderQuant, quantFilled, quantRemain);
    }

    /**
     * Buy order constructor that connects to GUI for new orders. It creates a new instance and adds it to the database.
     * Upon entering to database, it will attempt to execute the buy order.
     *
     * @param userID
     * @param assetID
     * @param orderPrice
     * @param orderQuant
     * @throws SQLException
     * @throws InvalidAssetException
     * @throws InvalidOrderException
     * @throws NegativePriceException
     * @throws InsuffcientFundsException
     */
    public BuyOrder(String userID, String assetID, double orderPrice, int orderQuant)
            throws SQLException, InvalidAssetException, InvalidOrderException,
            NegativePriceException, InsuffcientFundsException, UnitException {
        super(userID, assetID, OrderType.BUY, orderPrice, orderQuant);

        // Check enough credits to buy
        double orderCost = orderPrice * orderQuant;
        Unit currentUnit = Unit.getUnit(this.unitID);
        if (currentUnit.creditBalance < orderCost) {
            throw new InsuffcientFundsException("Order Error: Insufficient Credit Balance");
        }

        // Add to database
        AddOrderDatabase();

        // Attempt to execute order
        ExecuteBuyOrder();
    }

    /**
     * Algorithm to sequence matching orders and attempt to execute the buy order with existing sell orders
     * @throws SQLException
     * @throws InvalidOrderException
     * @throws InvalidAssetException
     * @throws NegativePriceException
     * @throws UnitException
     */
    public void ExecuteBuyOrder() throws SQLException, InvalidOrderException, InvalidAssetException, NegativePriceException, UnitException {
        // Current asset instance
        Asset currentAsset = Asset.findAsset(this.assetID);

        // Check if just ordered asset has corresponding order

        // Find sell orders of same asset
        Statement smt = connection.createStatement();
        String sqlFindOrder
                = "SELECT * FROM orders WHERE assetID = '" + assetID + "' and orderType = 'SELL' " +
                "and orderStatus = 'INCOMPLETE' and unitID != '" + this.unitID + "' " +
                "ORDER BY orderTime;";
        ResultSet sellOrders = smt.executeQuery(sqlFindOrder);

        // List to store corresponding sell orders of the same asset
        ArrayList<Order> matchingOrders = new ArrayList<>();

        // Add queried rows as new Sell Orders in list
        while (sellOrders.next()) {
            matchingOrders.add(
                    new SellOrder(
                            sellOrders.getString("orderID"),
                            sellOrders.getString("userID"),
                            sellOrders.getString("unitID"),
                            sellOrders.getString("assetID"),
                            sellOrders.getString("orderTime").substring(0,19),
                            OrderStatus.valueOf(sellOrders.getString("orderStatus")),
                            OrderType.valueOf(sellOrders.getString("orderType")),
                            sellOrders.getDouble("orderPrice"),
                            sellOrders.getInt("orderQuantity"),
                            sellOrders.getInt("quantFilled"),
                            sellOrders.getInt("quantRemain")
                    )
            );
        }

        // Remove orders if the sell ask is greater than the buy bid
        matchingOrders.removeIf(sells -> (sells.orderPrice > this.orderPrice));

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
        //      - Have enough sell orders to fully facilitate the buy order
        //          * Complete buy order (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Update buyer and seller inventory numbers
        //          * Change sell order (INCOMPLETE, quantFilled, quantRemain)
        //      - Have enough sell orders to partially fill the buy order
        //          * Complete the corresponding sell order/s (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Change buy order (INCOMPLETE, change quant remain, change quant filled)
        //          * Add inventory entry and update corresponding inventory numbers

        for (int i = 0; i < requiredOrders.size(); i++) {
            // How much this order has remaining to fill
            int buyQuant = this.quantRemain;
            // How much can this corresponding order faciliate of this order
            int sellQuant = requiredOrders.get(i).quantRemain;

            // Track executed quantity
            int executed = 0;

            if (sellQuant > buyQuant) {
                // When this order has more than the quantity required to fill remaining units

                // Stores how many more units the sell order has over this buy order
                this.quantFilled += buyQuant;
                this.quantRemain -= buyQuant;

                // Deduct the amount remaining of the sell order from the quantity remaining in the buy order
                requiredOrders.get(i).quantRemain -= buyQuant;
                requiredOrders.get(i).quantFilled += buyQuant;
                executed = buyQuant;

                // Change quantities in database
                ChangeQuantRemainFilled(this.quantRemain, this.quantFilled);
                requiredOrders.get(i).ChangeQuantRemainFilled(requiredOrders.get(i).quantRemain, requiredOrders.get(i).quantFilled);

                // Set complete status of this order
                this.ChangeStatus(OrderStatus.COMPLETE);

            } else if (sellQuant == buyQuant) {
                // When this order has exactly the same amount required to fill remaining units

                // Stores how many more units the buy order has over this sell order
                this.quantFilled += buyQuant;
                this.quantRemain -= buyQuant;

                // Deduct the amount remaining of the sell order from the quantity remaining in the buy order
                requiredOrders.get(i).quantRemain -= buyQuant;
                requiredOrders.get(i).quantFilled += buyQuant;
                executed = buyQuant;

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
                requiredOrders.get(i).quantRemain -= sellQuant;
                requiredOrders.get(i).quantFilled += sellQuant;
                executed = sellQuant;

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
            currentAsset.SetPrice(this.orderPrice);

            // Change inventory amounts
            new InventoryItem(this.unitID, this.assetID, this.orderPrice, executed, this.orderID);
            new InventoryItem(requiredOrders.get(i).unitID, requiredOrders.get(i).assetID, this.orderPrice, -executed, requiredOrders.get(i).orderID);

            // Set credit balance of units
            Unit buyerUnit = Unit.getUnit(this.unitID);
            buyerUnit.ChangeUnitBalance(this.unitID, -this.orderPrice * executed);
            Unit sellerUnit = Unit.getUnit(requiredOrders.get(i).unitID);
            sellerUnit.ChangeUnitBalance(requiredOrders.get(i).unitID, this.orderPrice * executed);
        }

        // If requiredOrders is empty, no current buy orders are able to facilitate sell order

        // Modify watchlist balances

    }
}