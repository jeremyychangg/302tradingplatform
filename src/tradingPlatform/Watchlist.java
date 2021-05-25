package tradingPlatform;

import tradingPlatform.exceptions.AssetTypeException;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 *
 */
public class Watchlist {
    String unitID;
    private ArrayList<Asset> watchlist;

    /**
     *
     * @param unitID
     * @throws SQLException
     */
    public Watchlist(String unitID) throws SQLException, AssetTypeException {
        this.unitID = unitID;
        this.watchlist = GetWatchlist();
    }

    /**
     *
     * @return
     */
    public ArrayList<Asset> GetWatchlist() throws SQLException, AssetTypeException {
        ArrayList<Asset> unitWatchlist = new ArrayList<>();
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
            unitWatchlist.add(new Asset(wlResult.getString("assetName"), wlResult.getString("assetType"), wlResult.getDouble("currentPrice")));
        }

        this.watchlist = unitWatchlist;

        return this.watchlist;
    }

    /**
     *
     * @param asset
     * @return
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
     *
     * @param asset
     * @return
     * @throws SQLException
     */
    public boolean RemoveFromWatchlist(Asset asset) throws SQLException {
        boolean removeStatus;
        String removeID = asset.GetAssetID();

        PreparedStatement removeAsset =  connection.prepareStatement("DELETE FROM watchlists where unitID = '?' and assetID = '?';");
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
     *
     * @return
     * @throws SQLException
     */
    public int GetWatchlistCount() throws SQLException {
        int count;
        Statement statement = connection.createStatement();
        String sqlCount = "SELECT DISTINCT count(assetID) as count FROM watchlists WHERE unitID = '" + unitID + "';";
        ResultSet countResult = statement.executeQuery(sqlCount);

        count = countResult.getInt(1);

        return count;
    }



}
