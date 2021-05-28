// 	******************************************************************************************
// 	**																        **
// 	**	Filename: Inventory.java									             **
// 	**																	   **
// 	**	Description: Inventory Class						   **
// 	**													   	                  **
// 	**																	   **
// 	**	Contributors: Natalie Smith (n10524215)									   **
// 	**																	   **
// 	**																        **
// 	**	Date Created: 16/05/2021												   **
// 	**																	   **
// 	**																	   **
// 	**	Change Documentation											        **
// 	**		> Updated version										        **
// 	**																        **
// 	**																	   **
// 	**																	   **
// 	******************************************************************************************
//
package tradingPlatform;

import java.util.ArrayList;

public class Inventory {
    private int unitID;
    private int assetID;
    private int quantity;
    private int orderID;

    public Inventory(int unitID, int assetID, int quantity, int orderID){
        this.unitID = unitID;
        this.assetID = assetID;
        this.quantity = quantity;
        this.orderID = orderID;
    }

    public Inventory(String unitID, String assetID, int quantity, int orderID) {
    }

    public ArrayList<Asset> findAsset(String unitID) {
        return new ArrayList<Asset>();
    }
}
