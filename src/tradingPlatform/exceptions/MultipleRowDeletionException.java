// 	******************************************************************************************
// 	**																						**
// 	**	Filename: MultipleRowDeletionException.java  										**
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
 * Exception thrown when an intended single row query returns more than a row
 */
public class MultipleRowDeletionException extends Exception {
    /**
     * Exception thrown when an intended single row query returns more than a row
     * @param message Error message
     */
    public MultipleRowDeletionException(String message) {
        super(message);
    }
}
