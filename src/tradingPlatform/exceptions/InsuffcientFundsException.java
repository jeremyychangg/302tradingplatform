// 	******************************************************************************************
// 	**																						**
// 	**	Filename: InsufficientFundsException.java											**
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
 * Exception thrown when an order is made by a unit with insufficient credit balance to facilitate the buy order
 */
public class InsuffcientFundsException extends Exception {
    /**
     * Exception thrown when an order is made by a unit with insufficient credit balance to facilitate the buy order
     * @param message Error message
     */
    public InsuffcientFundsException(String message) {

        super(message);
    }
}
