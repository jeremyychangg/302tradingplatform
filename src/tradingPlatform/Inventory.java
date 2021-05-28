package tradingPlatform;

import java.util.ArrayList;

public class Inventory {
    String unitID;
    ArrayList<Asset> unitInventory;

    public Inventory(String unitID, String assetID, int quantity) {
    }

    public Inventory(String unitID, String assetID, int quantity, int orderID) {
    }

    public ArrayList<Asset> findAsset(String unitID) {
        return new ArrayList<Asset>();
    }
}
