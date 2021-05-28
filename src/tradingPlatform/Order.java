// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Order.java			    												**
// 	**																						**
// 	**	Description: Orders are executed through this class									**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 26/05/2021															**
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
import tradingPlatform.exceptions.MultipleRowDeletionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tradingPlatform.Main.connection;

public class Order {
    String orderID;
    String userID;
    String unitID;
    String assetID;
    LocalDateTime orderTime;
    OrderStatus orderStatus;
    OrderType orderType;
    double orderPrice;
    int orderQuant;
    int quantFilled;
    int quantRemain;

    public Order(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
            OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        this.orderID = orderID;
        this.userID = userID;
        this.unitID = unitID;
        this.assetID = assetID;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.orderTime = LocalDateTime.parse(orderTime, dtf);

        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = quantFilled;           // Initial quantity filled is 0
        this.quantRemain = quantRemain;
    }


    public Order(String userID, String assetID, OrderType orderType,
                 double orderPrice, int orderQuant) throws SQLException {
        this.userID = userID;
        this.assetID = assetID;
        this.orderStatus = OrderStatus.INCOMPLETE;      // Initialise new order as incomplete
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = 0;           // Initial quantity filled is 0
        this.quantRemain = orderQuant;  // Quantity remaining is initial order quantity

        // Get unitID from user information
        // this.unitID = userID.GetUnitID();
        this.unitID = "FI00000008";

        // Get current date time
        this.orderTime = LocalDateTime.now();





    }

    public void AddOrderDatabase() throws SQLException, InvalidAssetException {
        // Store order type
        String orderSubstring;
        if (orderType == OrderType.BUY) {
            orderSubstring = "BY";
        } else {
            orderSubstring = "SL";
        }


        // Check if asset exists
        try {
            if (!CheckAssetExists(this.assetID)) {
                throw new InvalidAssetException("Selected Asset is Invalid");
            }
        } catch (InvalidAssetException e) {
            System.out.println(e.getMessage());
        }


        // Get highest orderID from database
        int maxID = 0;
        Statement smt = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(orderID, 3, 8)) as maxID FROM orders WHERE substring(orderID, 1, 2) = '"
                + orderSubstring + "';";
        ResultSet getMaxID = smt.executeQuery(sqlMaxID);

        // Extract string result and parse as integer
//        if (getMaxID.next() == true) {
//            while (getMaxID.next()) {
//                maxID = Integer.parseInt(getMaxID.getString("maxID"));
//            }
//        }
        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            maxID = Integer.parseInt(getMaxID.getString("maxID"));
        }

        String newID = orderSubstring + String.format("%08d", maxID + 1);
        this.orderID = newID;

//        insert fields into database
        PreparedStatement newOrder =
                connection.prepareStatement(
                        "INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?,?,?);");

        newOrder.setString(1, this.orderID);
        newOrder.setString(2, this.userID);
        newOrder.setString(3, this.unitID);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        newOrder.setString(4, dtf.format(this.orderTime));

        newOrder.setString(5, this.assetID);
        newOrder.setString(6, this.orderStatus.name());
        newOrder.setString(7, this.orderType.name());
        newOrder.setDouble(8, this.orderPrice);
        newOrder.setInt(9, this.orderQuant);
        newOrder.setInt(10, this.quantFilled);
        newOrder.setInt(11, this.quantRemain);

        try {
            newOrder.execute();
        } catch (SQLException e) {
            System.out.println("New Order Error: " + e.getMessage());
        }

    }

    public boolean CheckAssetExists(String assetID) throws SQLException {
        Statement smt = connection.createStatement();
        String sqlCheckAsset
                = "SELECT assetID FROM assets WHERE assetID = '" + assetID + "';";
        ResultSet assetIDs = smt.executeQuery(sqlCheckAsset);

        if (assetIDs.next() == false) {
            return false;
        } else  {
            return true;
        }
    }

    public void DeleteOrder() throws SQLException, MultipleRowDeletionException {
        Statement smt = connection.createStatement();
        String sqlDelOrder
                = "DELETE FROM orders WHERE orderID = '" + this.orderID + "';";
        int delNum = smt.executeUpdate(sqlDelOrder);

        if (delNum > 1) {
            throw new MultipleRowDeletionException("Warning: Multiple rows were deleted from this query");
        }
    }



}

//Check asset exists


//Edit buy price edit sell price