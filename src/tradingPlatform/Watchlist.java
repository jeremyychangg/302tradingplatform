// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Watchlist.java															**
// 	**																						**
// 	**	Description: Class for individual watchlists per organisational unit				**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 18/05/2021															**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************


package tradingPlatform;

import tradingPlatform.exceptions.AssetTypeException;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 * Watchlist of a unit when considering purchasing an asset
 */
public class Watchlist {
    // Watchlist fields
    String unitID;
    private ArrayList<Asset> watchlist;

    /**
     * Finds watchlist of associated units and creates a list of assets
     * @param unitID
     * @throws SQLException
     */
    public Watchlist(String unitID) throws SQLException, AssetTypeException {
        this.unitID = unitID;
        this.watchlist = GetWatchlist();
    }

    /**
     * Queries database and finds current watchlist for a given unit
     * @return
     */
    public ArrayList<Asset> GetWatchlist() throws SQLException, AssetTypeException {
        ArrayList<Asset> unitWatchlist = new ArrayList<>();

        // Joins with asset table to find current asset info
        String getWatchlist =
                        "SELECT DISTINCT " +
                            "a.assetID, " +
                            "b.* " +
                        "FROM " +
                            "watchlists as a " +
                                "LEFT JOIN " +
                            "assets as b " +
                                "ON a.assetID = b.assetID " +
                        "WHERE unitID = '" + unitID + "';";

        Statement statement = connection.createStatement();
        ResultSet wlResult = statement.executeQuery(getWatchlist);

        // Loop through ResultSet and append fields as new assets in unitWatchlist
        while (wlResult.next()) {
            unitWatchlist.add(new Asset(
                    wlResult.getString("assetName"),
                    wlResult.getString("assetType"),
                    wlResult.getDouble("currentPrice")
                    )
            );
        }

        // Set watchlist and return
        this.watchlist = unitWatchlist;
        return this.watchlist;
    }

    /**
     * Adds a given asset to the watchlist of a unit
     * @param asset
     * @return If added return true, else false
     * @throws SQLException
     */
    public boolean AddToWatchlist(Asset asset) throws SQLException {
        boolean addStatus;
        String addID = asset.GetAssetID();

        PreparedStatement addAsset =  connection.prepareStatement("INSERT INTO watchlists (unitID, assetID) VALUES (?,?);");
        addAsset.clearParameters();
        addAsset.setString(1, unitID);
        addAsset.setString(2, addID);
        addStatus = addAsset.execute();

        watchlist.add(asset);

        if (addStatus == true){
            return true;
        } else {
            throw new SQLException();
        }
    }

    /**
     * Removes a given asset from a unit's watchlist
     * @param asset
     * @return If removed return true, else return false
     * @throws SQLException
     */
    public boolean RemoveFromWatchlist(Asset asset) throws SQLException {
        boolean removeStatus;
        String removeID = asset.GetAssetID();

        PreparedStatement removeAsset =  connection.prepareStatement("DELETE FROM watchlists where unitID = ? and assetID = ?;");
        removeAsset.clearParameters();
        removeAsset.setString(1, unitID);
        removeAsset.setString(2, removeID);
        removeStatus = removeAsset.execute();


        watchlist.remove(asset);

        if (removeStatus == true){
            return true;
        } else {
            throw new SQLException();
        }
    }

    /**
     * Gets the number of assets a unit has in their watchlist
     * @return
     * @throws SQLException
     */
    public int GetWatchlistCount() throws SQLException {
        int count = 0;
        Statement statement = connection.createStatement();
        String sqlCount = "SELECT DISTINCT count(assetID) as count FROM watchlists WHERE unitID = '" + unitID + "';";
        ResultSet countResult = statement.executeQuery(sqlCount);

        if (countResult.next()) {
            count = countResult.getInt(1);
        }
        return count;
    }
}
