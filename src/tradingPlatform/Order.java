package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;

import java.time.LocalDateTime;

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
                 double orderPrice, int orderQuant) {
        this.userID = userID;
        this.assetID = assetID;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = 0;   // Initial quantity filled is 0
        this.quantRemain = orderQuant;     // Quantity remaining is initial order quantity

//        Find method to get unit ID from user ID
//        Create order ID
//        Get current date time
    }

    public void AddOrderDatabase(){
//        insert fields into database
    }
}



//Edit buy price edit sell price