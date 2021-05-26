package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;

public class BuyOrder extends Order {


    public BuyOrder(String userID, String assetID, OrderStatus orderStatus, OrderType orderType,
                    double orderPrice, int orderQuant) {
        super(userID, assetID, orderStatus, orderType, orderPrice, orderQuant);
    }
}
