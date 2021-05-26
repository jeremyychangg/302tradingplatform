package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;

public class SellOrder extends Order{


    public SellOrder(String userID, String assetID, OrderStatus orderStatus, OrderType orderType,
                     double orderPrice, int orderQuant) {
        super(userID, assetID, orderStatus, orderType, orderPrice, orderQuant);
    }
}
