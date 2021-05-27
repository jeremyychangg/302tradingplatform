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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;

public class BuyOrder extends Order {
    public BuyOrder(String userID, String assetID, double orderPrice, int orderQuant) throws SQLException {
        super(userID, assetID, OrderType.BUY, orderPrice, orderQuant);

        // Create order ID
        // Get highest ID value existing in database
//        int maxID;
//        Statement statement = connection.createStatement();
//        String sqlMaxID
//                = "SELECT max(substring(orderID, 3, 8)) as maxID FROM orders WHERE orderType = 'BUY';";
//        ResultSet getMaxID = statement.executeQuery(sqlMaxID);
//
//        // Extract string result and parse as integer
//        maxID = Integer.parseInt(getMaxID.getString("maxID"));
//
//        // Add 1 to current max ID to get new ID number for this asset and append to asset type code
//        String newID = "BY" + String.format("%08d", maxID + 1);
//
//        // Assign BUY orderID to order
//        this.orderID = newID;
    }
}
