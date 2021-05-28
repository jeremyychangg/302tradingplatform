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
import tradingPlatform.exceptions.InvalidAssetException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

public class SellOrder extends Order{
    public SellOrder(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
                     OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        super(orderID, userID, unitID, assetID, orderTime, orderStatus,
                orderType, orderPrice, orderQuant, quantFilled, quantRemain);
    }

    public SellOrder(String userID, String assetID, double orderPrice, int orderQuant)
            throws SQLException, InvalidAssetException {

        super(userID, assetID, OrderType.SELL, orderPrice, orderQuant);

        AddOrderDatabase();

        ExecuteSellOrder();
    }

    public void ExecuteSellOrder() throws SQLException {
        // Check if just ordered asset has corresponding order

        // Find sell orders of same asset
        Statement smt = connection.createStatement();
        String sqlFindOrder
                = "SELECT * FROM orders WHERE assetID = '" + assetID + "' and orderType = 'BUY';";
        ResultSet buyOrders = smt.executeQuery(sqlFindOrder);

        ArrayList<Order> matchingOrders = new ArrayList<>();

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

        matchingOrders.forEach((x) -> System.out.println(x.orderID + ", " + x.orderType + ", " + x.userID + ", " + x.assetID));
        //        If match execute buy and sell order
        //        match quantity and price
        //        Find the earliest orderTime

        //        Filter by asset, then by price, then by time, then assess quantity and find different, then edit fields/set complete
        //        Change inventory - maybe add that class


    }
}
