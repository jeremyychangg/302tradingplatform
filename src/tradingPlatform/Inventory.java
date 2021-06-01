// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Inventory.java															**
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

public class Inventory {
    String unitID;
    ArrayList<InventoryItem> unitInventory;
    double inventorySize;

    public Inventory(String unitID) throws SQLException, InvalidAssetException {
        this.unitID = unitID;
        this.unitInventory = findInventory(unitID);

    }

    public ArrayList<InventoryItem> findInventory(String unitID) throws SQLException, InvalidAssetException {
        ArrayList<InventoryItem> inventory = new ArrayList<>();

        // Query all inventory items
        Statement statement = connection.createStatement();
        String sqlInventory
                =   "SELECT " +
                    "assetID, sum(quantity) as quantity, sum(price)/sum(quantity) as averagePrice " +
                    "FROM inventory " +
                    "WHERE unitID = '" + unitID + "' " +
                    "GROUP BY assetID;"
               ;
        ResultSet getInventory = statement.executeQuery(sqlInventory);

        while (getInventory.next()) {
            inventory.add(
                    new InventoryItem(
                            getInventory.getString("assetID"),
                            (double) getInventory.getFloat("averagePrice"),
                            getInventory.getInt("quantity")

                    )
            );

            inventorySize += (double) getInventory.getFloat("averagePrice") * getInventory.getInt("quantity");
            // Consider adding credit balance into here
        }

        return inventory;
    }
}
