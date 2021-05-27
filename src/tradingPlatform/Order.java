package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void AddOrderDatabase() throws SQLException {
        // Store order type
        String orderSubstring;
        if (orderType == OrderType.BUY) {
            orderSubstring = "BY";
        } else {
            orderSubstring = "SL";
        }

        // Get highest orderID from database
        int maxID = 0;
        Statement smt = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(orderID, 3, 8)) as maxID FROM orders WHERE substring(assetID, 1, 2) = '"
                + orderSubstring + "';";
        ResultSet getMaxID = smt.executeQuery(sqlMaxID);

        // Extract string result and parse as integer
        while (getMaxID.next()) {
            // If an order does not exist yet
            if (getMaxID.getString("maxID") == "") {
                maxID = 0;
            } else {
                maxID = Integer.parseInt(getMaxID.getString("maxID"));
            }
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        newOrder.setString(4, dtf.format(this.orderTime));

        newOrder.setString(5, this.assetID);
        newOrder.setString(6, this.orderStatus.name());
        newOrder.setString(7, this.orderType.name());
        newOrder.setDouble(8, this.orderPrice);
        newOrder.setInt(9, this.orderQuant);
        newOrder.setInt(10, this.quantFilled);
        newOrder.setInt(11, this.quantRemain);

        newOrder.execute();
        connection.close();
    }
}


//Check user exists
//
//Check unit exists

//Check asset exists

//Edit buy price edit sell price