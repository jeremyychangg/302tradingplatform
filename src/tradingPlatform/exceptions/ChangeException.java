// 	******************************************************************************************
// 	**																						**
// 	**	Filename: ChangeException.java  													**
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
 * Exception thrown when unexpected change occurs.
 */
public class ChangeException extends Exception {
    /**
     * Exception thrown when unexpected change occurs.
     * @param message Error message
     */
    public ChangeException(String message) {
        super(message);
    }
}
