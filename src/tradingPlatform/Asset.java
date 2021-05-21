package tradingPlatform;

public class Asset {

    public String assetName;
    public String assetType;
    private double currentPrice;

    public Asset(String assetName, String assetType) {
        this.assetName = assetName;
        this.assetType = assetType;
    }


    public Asset(String assetName, String assetType, double currentPrice)  {
        this.assetName = assetName;
        this.assetType = assetType;
        this.currentPrice = currentPrice;
    }

    public String GetAssetID() {
        return "A...";
    }

    public String GetAssetName() {
        return "AssetName";
    }

    public double GetPrice() {
        return 0;
    }

    public void setPrice(double price) {
        this.currentPrice = price;
    }

    public double getPrice() {
        return this.currentPrice;
    }
}
