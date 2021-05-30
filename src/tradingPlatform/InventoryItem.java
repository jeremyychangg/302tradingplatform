// 	******************************************************************************************
// 	**																						**
// 	**	Filename: InventoryItem.java											    		**
// 	**																						**
// 	**	Description:																		**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created:																		**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************

package tradingPlatform;

import tradingPlatform.exceptions.InvalidAssetException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

public class InventoryItem {
    Asset asset;
    double purchasePrice;
    int quantity;
    String orderID = "";    // Initialise in case null

    /**
     * This constructor is used if the inventory item was originally in the unit's possession without an order
     * @param assetID
     * @param purchasePrice
     * @param quantity
     */
    public InventoryItem(String assetID, double purchasePrice, int quantity) throws SQLException, InvalidAssetException {
        this.asset = findAsset(assetID);
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;

        try {
            AddToDatabase();
        } catch (SQLException e) {
            System.out.println("New Inventory Item Error: " + e.getMessage());
        }

    }

    /**
     * This constructor is used if the inventory item was originally in the unit's possession without an order and no price
     * @param assetID
     * @param quantity
     */
    public InventoryItem(String assetID, int quantity) throws SQLException, InvalidAssetException {
        new InventoryItem(assetID, 0, quantity);
    }


    /**
     * This constructor is used if the inventory item was acquired through an order
     * @param assetID
     * @param purchasePrice
     * @param quantity
     * @param orderID
     */
    public InventoryItem(String assetID, double purchasePrice, int quantity, String orderID) throws SQLException, InvalidAssetException {
        this.asset = findAsset(assetID);
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.orderID = orderID;

        try {
            AddToDatabase();
        } catch (SQLException e) {
            System.out.println("New Inventory Item Error: " + e.getMessage());
        }

    }

    /**
     *
     * @param assetID
     * @return
     */
    public Asset findAsset(String assetID) throws SQLException, InvalidAssetException {
        Asset matchingAsset;

        Statement statement = connection.createStatement();
        String sqlFindAsset = "SELECT * from assets WHERE assetID = '" + assetID + "';";
        ResultSet getAsset = statement.executeQuery(sqlFindAsset);

        if (getAsset.next() && getAsset.getString("assetID") != null) {
            matchingAsset = new Asset(
                    getAsset.getString("assetID"),
                    getAsset.getString("assetName"),
                    getAsset.getString("assetType")
            );
        } else {
            throw new InvalidAssetException("Asset not found.");
        }

        return matchingAsset;
    }

    public void AddToDatabase() throws SQLException {
        PreparedStatement newInvItem =
                connection.prepareStatement("INSERT INTO inventory (unitID, assetID, orderID, quantity, price) VALUES (?,?,?,?,?);");

//        newInvItem.setString(1, Main.unitID);       // Find method/variable which stores unit ID
        newInvItem.setString(2, this.asset.GetAssetID());
        newInvItem.setString(3, this.orderID);
        newInvItem.setInt(4, this.quantity);
        newInvItem.setDouble(5, this.purchasePrice);

        newInvItem.execute();
    }
}
