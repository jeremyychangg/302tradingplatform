// 	******************************************************************************************
// 	**																						**
// 	**	Filename: ReconciliationException.java  											**
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
 * Exception thrown when orders and inventory don't reconcile to expected asset numbers
 */
public class ReconciliationError extends Exception {
    /**
     * Exception thrown when orders and inventory don't reconcile to expected asset numbers
     * @param message Error message
     */
    public ReconciliationError(String message) {
        super(message);
    }
}
