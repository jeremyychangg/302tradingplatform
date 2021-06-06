// 	******************************************************************************************
// 	**																						**
// 	**	Filename: InvalidOrderException.java  												**
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
 * Exception thrown when an invalid order field is inputted
 */
public class InvalidOrderException extends Exception {
    /**
     * Exception thrown when an invalid order field is inputted
     * @param message Error message
     */
    public InvalidOrderException(String message) {
        super(message);
    }
}
