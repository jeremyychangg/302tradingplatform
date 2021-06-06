// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Asset.java									    						**
// 	**																						**
// 	**	Description: Assets that are traded on the marketplace  							**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 21/05/2021															**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************

package tradingPlatform;

import tradingPlatform.exceptions.*;

import java.sql.*;

import static tradingPlatform.Main.connection;

/**
 * Used to manipulate different assets in system
 */
public class Asset {
    // Asset fields
    private String assetID;
    public String assetName;
    public String assetType;
    private double currentPrice;

    /**
     * For creating asset instance when it already exists in database
     * @param assetID
     * @param assetName
     * @param assetType
     */
    public Asset(String assetID, String assetName, String assetType, double currentPrice) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetType = assetType;
        this.currentPrice = currentPrice;
    }

    /**
     * For adding a new asset into the system without a price yet
     * @param assetName
     * @param assetType
     */
    public Asset(String assetName, String assetType) throws SQLException, AssetTypeException {
        this.assetName = assetName;
        this.assetType = assetType;

        String IDsubstring = "";

        // Find asset type for new ID
        if (assetType == "Computer Accessories") {
            IDsubstring = "CA";
        } else if (assetType == "Furniture") {
            IDsubstring = "FN";
        } else if (assetType == "Office Supplies") {
            IDsubstring = "OS";
        } else if (assetType == "Services") {
            IDsubstring = "SV";
        } else if (assetType == "Technology") {
            IDsubstring = "TC";
        } else if (assetType == "Miscellaneous") {
            IDsubstring = "MS";
        } else {
            throw new AssetTypeException("Invalid Asset Type");
        }

        // Get highest ID value existing in database
        int maxID = 0;
        Statement statement = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(assetID, 3, 8)) as maxID FROM assets WHERE substring(assetID, 1, 2) = '"
                + IDsubstring + "';";
        ResultSet getMaxID = statement.executeQuery(sqlMaxID);

        // Extract string result and parse as integer
        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            maxID = Integer.parseInt(getMaxID.getString("maxID"));
        }

        // Add 1 to current max ID to get new ID number for this asset and append to asset type code
        String newID = IDsubstring + String.format("%08d", maxID + 1);
        this.assetID = newID;

        PreparedStatement newAsset =
                connection.prepareStatement("INSERT INTO assets (assetID, assetName, assetType) VALUES (?,?,?);");

        newAsset.setString(1, newID);
        newAsset.setString(2, assetName);
        newAsset.setString(3, assetType);

        newAsset.execute();
    }

    /**
     * Creates asset instance with asset with a price
     * @param assetName
     * @param assetType
     * @param currentPrice
     * @throws AssetTypeException
     * @throws SQLException
     */
    public Asset(String assetName, String assetType, double currentPrice) throws AssetTypeException, SQLException {
        this.assetName = assetName;
        this.assetType = assetType;
        this.currentPrice = currentPrice;

        String IDsubstring = "";

        if (assetType == "Computer Accessories") {
            IDsubstring = "CA";
        } else if (assetType == "Furniture") {
            IDsubstring = "FN";
        } else if (assetType == "Office Supplies") {
            IDsubstring = "OS";
        } else if (assetType == "Services") {
            IDsubstring = "SV";
        } else if (assetType == "Technology") {
            IDsubstring = "TC";
        } else if (assetType == "Miscellaneous") {
            IDsubstring = "MS";
        } else {
            throw new AssetTypeException("Invalid Asset Type");
        }

        // Get highest ID value existing in database
        int maxID = 0;
        Statement statement = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(assetID, 3, 8)) as maxID FROM assets WHERE substring(assetID, 1, 2) = '"
                + IDsubstring + "';";
        ResultSet getMaxID = statement.executeQuery(sqlMaxID);

        // If result set contains something, then assign maxID
        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            // Extract string result and parse as integer
            maxID = Integer.parseInt(getMaxID.getString("maxID"));
        }

        // Add 1 to current max ID to get new ID number for this asset and append to asset type code
        String newID = IDsubstring + String.format("%08d", maxID + 1);
        this.assetID = newID;

        PreparedStatement newAsset =
                connection.prepareStatement("INSERT INTO assets VALUES (?,?,?,?);");
        newAsset.clearParameters();
        newAsset.setString(1, newID);
        newAsset.setString(2, assetName);
        newAsset.setDouble(3, currentPrice);
        newAsset.setString(4, assetType);

        try {
            newAsset.execute();
        } catch (SQLException e) {
            System.out.println("New Asset Error: " + e.getMessage());
        }
    }

    /**
     * Getter method to return a private assetID
     * @return AssetID of queried asset
     */
    public String GetAssetID() {
        return this.assetID;
    }

    /**
     * Getter method to return name of asset
     * @return Asset name of queried asset
     */
    public String GetAssetName() {
        return this.assetName;
    }

    /**
     * Getter method to return asset price
     * @return Asset price
     */
    public double GetPrice() {
        return this.currentPrice;
    }

    /**
     * Sets the price of an asset
     * @param price
     * @throws SQLException
     * @throws NegativePriceException
     */
    public void SetPrice(double price) throws SQLException, NegativePriceException {
        if (price < 0) {
            throw new NegativePriceException("Asset price cannot be negative");
        } else {
            this.currentPrice = price;

            String sqlPrice = "UPDATE assets SET currentPrice = ? WHERE assetID = ?;";
            PreparedStatement changePrice = connection.prepareStatement(sqlPrice);
            changePrice.clearParameters();
            changePrice.setDouble(1, price);
            changePrice.setString(2, assetID);
            changePrice.executeUpdate();
        }
    }

    /**
     * Removes asset from system
     * @param assetID
     * @throws SQLException
     * @throws AssetRemovalException
     * @throws MultipleRowDeletionException
     */
    public void RemoveAsset(String assetID) throws SQLException, AssetRemovalException, MultipleRowDeletionException {
        // Delete from database
        PreparedStatement removeAsset =  connection.prepareStatement("DELETE FROM assets WHERE assetID = ?;");
        removeAsset.clearParameters();
        removeAsset.setString(1, assetID);
        int rowsDeleted = removeAsset.executeUpdate();

        // Throw error if no asset was removed or if too many assets removed
        if (rowsDeleted == 0) {
            throw new AssetRemovalException("No asset was removed");
        } else if (rowsDeleted > 0) {
            throw new MultipleRowDeletionException("Warning: Multiple rows were deleted from this query");
        }
    }

    /**
     * Finds asset given its provided an assetID
     * @param assetID
     * @return Returns instance of an asset
     */
    public static Asset findAsset(String assetID) throws SQLException, InvalidAssetException {
        Asset matchingAsset;

        // Queries database on assetID
        Statement statement = connection.createStatement();
        String sqlFindAsset = "SELECT * from assets WHERE assetID = '" + assetID + "';";
        ResultSet getAsset = statement.executeQuery(sqlFindAsset);

        // Returns new asset if found
        if (getAsset.next() && getAsset.getString("assetID") != null) {
            matchingAsset = new Asset(
                    getAsset.getString("assetID"),
                    getAsset.getString("assetName"),
                    getAsset.getString("assetType"),
                    getAsset.getDouble("currentPrice")
            );
        } else {
            throw new InvalidAssetException("Asset not found.");
        }
        return matchingAsset;
    }
}
