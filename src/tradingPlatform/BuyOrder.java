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
import tradingPlatform.exceptions.InvalidAssetException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

public class BuyOrder extends Order {
    public BuyOrder(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
                    OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        super(orderID, userID, unitID, assetID, orderTime, orderStatus,
                orderType, orderPrice, orderQuant, quantFilled, quantRemain);
    }

    public BuyOrder(String userID, String assetID, double orderPrice, int orderQuant)
            throws SQLException, InvalidAssetException {
        super(userID, assetID, OrderType.BUY, orderPrice, orderQuant);

        AddOrderDatabase();

        ExecuteBuyOrder();
    }

    public void ExecuteBuyOrder() throws SQLException {
        // Check if just ordered asset has corresponding order

        // Find sell orders of same asset
        Statement smt = connection.createStatement();
        String sqlFindOrder
                = "SELECT * FROM orders WHERE assetID = '" + assetID + "' and orderType = 'SELL' " +
                "and orderStatus = 'INCOMPLETE'" +
                "ORDER BY orderTime;";
        ResultSet sellOrders = smt.executeQuery(sqlFindOrder);

        //        If match execute buy and sell order
//        match quantity and price

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
                            sellOrders.getString("orderTime"),
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

        // Required orders to facilitate quantity of this order
        int requiredQuant = this.orderQuant;

        // Loop through orders matching conditions and append as many orders possible to
        // fully/partially facilitate this order
        for (int i = 0; i < matchingOrders.size(); i++) {
            // Stores quantity of current order in matchingOrders list
            int quantity = matchingOrders.get(i).orderQuant;
            if (requiredQuant > 0) {
                requiredOrders.add(matchingOrders.get(i));
            }
            requiredQuant -= quantity;
        }


        // If requiredOrders is empty, no current buy orders are able to facilitate sell order

        // If requiredOrders is filled it can either
        //      - Have enough buy orders to fully facilitate the sell order
        //          * Complete sell order (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Update buyer and seller inventory numbers
        //          * Change buy order (INCOMPLETE, quantFilled, quantRemain)
        //      - Have enough buy orders to partially fill the sell order
        //          * Complete the corresponding buy order/s (set COMPLETE flag, 0 quant remain, max quant fill)
        //          * Change sell order (INCOMPLETE, change quant remain, change quant filled)
        //          * Add inventory entry and update corresponding inventory numbers


        matchingOrders.forEach((x) -> System.out.println(x.orderID + ", " + x.orderType + ", " + x.userID + ", " + x.assetID));

    }


}
