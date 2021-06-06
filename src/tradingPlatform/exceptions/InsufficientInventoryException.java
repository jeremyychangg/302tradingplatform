// 	******************************************************************************************
// 	**																						**
// 	**	Filename: InsufficientInventoryException.java  										**
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
 * Exception called unit attempts to sell inventory that they do not have.
 */
public class InsufficientInventoryException extends Exception {
    /**
     * Exception called unit attempts to sell inventory that they do not have.
     * @param message Error message
     */
    public InsufficientInventoryException(String message) {
        super(message);
    }
}
