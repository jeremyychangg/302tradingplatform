// 	******************************************************************************************
// 	**																						**
// 	**	Filename: InvalidAssetException.java  												**
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

package tradingPlatform.exceptions;

/**
 * Exception thrown when an invalid asset field is inputted
 */
public class InvalidAssetException extends Exception {
    /**
     * Exception thrown when an invalid asset field is inputted
     * @param message Error message
     */
    public InvalidAssetException(String message) {
        super(message);
    }

}
