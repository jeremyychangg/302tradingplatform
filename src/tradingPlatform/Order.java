package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;

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

    public Order(String userID, String assetID, OrderStatus orderStatus, OrderType orderType,
                 double orderPrice, int orderQuant) throws SQLException {
        this.userID = userID;
        this.assetID = assetID;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = 0;           // Initial quantity filled is 0
        this.quantRemain = orderQuant;  // Quantity remaining is initial order quantity

        // Get unitID from user information
        // this.unitID = userID.GetUnitID();

        // Get current date time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime currentTime = dtf.format(LocalDateTime.now());

    }

    public void AddOrderDatabase(){
//        insert fields into database
    }
}



//Edit buy price edit sell price