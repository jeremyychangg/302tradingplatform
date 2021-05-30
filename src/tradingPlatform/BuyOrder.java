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
                = "SELECT * FROM orders WHERE assetID = '" + assetID + "' and orderType = 'SELL'" +
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


        matchingOrders.forEach((x) -> System.out.println(x.orderID + ", " + x.orderType + ", " + x.userID + ", " + x.assetID));

    }


}
