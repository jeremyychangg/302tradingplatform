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

import tradingPlatform.exceptions.InsufficientInventoryException;
import tradingPlatform.exceptions.InvalidAssetException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 * Acts as a storage of a unit's current inventory
 */
public class Inventory {
    // Inventory fields
    String unitID;
    public ArrayList<InventoryItem> unitInventory;
    public double inventorySize;

    /**
     * Constructor finds the inventory of a specific unit and creates a list of inventory items
     * @param unitID
     * @throws SQLException
     * @throws InvalidAssetException
     */
    public Inventory(String unitID) throws SQLException, InvalidAssetException {
        this.unitID = unitID;
        this.unitInventory = findInventory(unitID);
    }

    /**
     * Method finds the inventory of any unit through a list of inventory items
     * @param unitID
     * @return ArrayList containing inventory items (unique assets)
     * @throws SQLException
     * @throws InvalidAssetException
     */
    public ArrayList<InventoryItem> findInventory(String unitID) throws SQLException, InvalidAssetException {
        ArrayList<InventoryItem> inventory = new ArrayList<>();

        // Query all inventory items (sums each of the inventory transactions and groups them by assets
        Statement statement = connection.createStatement();
        String sqlInventory
                =   "SELECT " +
                    "assetID, sum(quantity) as quantity, sum(price)/sum(quantity) as averagePrice " +
                    "FROM inventory " +
                    "WHERE unitID = '" + unitID + "' " +
                    "GROUP BY assetID;"
               ;
        ResultSet getInventory = statement.executeQuery(sqlInventory);

        // Add each inventory item into list
        while (getInventory.next()) {
            inventory.add(
                    new InventoryItem(
                            getInventory.getString("assetID"),
                            (double) getInventory.getFloat("averagePrice"),
                            getInventory.getInt("quantity")
                    )
            );

            // Adds portfolio value of inventory
            inventorySize += (double) getInventory.getFloat("averagePrice") * getInventory.getInt("quantity");
            // Consider adding credit balance into here
        }
        return inventory;
    }

    /**
     * Finds if a specific asset is in the units inventory
     * @param assetID
     * @return True: Exists, False: Does not exist
     */
    public boolean AssetInInventory(String assetID) {
        // Loop through unit's inventory and see if there are matching assetIDs
        for (int i = 0; i < unitInventory.size(); i++) {
            if (unitInventory.get(i).asset.GetAssetID() == assetID) {
                return true;
            }
        }
        // If not return false
        return false;
    }

    /**
     *
     * Loops through unit's inventory to find if certain asset has sufficient quantity
     * to facilitate order quantity.
     *
     * @param assetID Asset's ID to find in inventory
     * @param orderQuantity Quantity of specific order that will be executed
     * @return True: Sufficient Quantity, False: Insufficient Quantity
     * @throws InvalidAssetException
     * @throws InsufficientInventoryException
     */
    public boolean SufficientAssetQuantity(String assetID, int orderQuantity)
            throws InvalidAssetException, InsufficientInventoryException {
        // Throw error if asset isn't even in inventory
        if (!(AssetInInventory(assetID))) {
            throw new InvalidAssetException("Inventory Error: Order asset not in inventory.");
        }

        // Loops through inventory to find asset quantity
        for (int i = 0; i < unitInventory.size(); i++) {
            if (unitInventory.get(i).asset.GetAssetID() == assetID ) {
                if (unitInventory.get(i).quantity >= orderQuantity) {
                    return true;
                } else {
                    throw new InsufficientInventoryException("Inventory Error: Insufficient asset quantity in order");
                }
            }
        }
        return false;
    }
}
