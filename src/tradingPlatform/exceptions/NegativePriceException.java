// 	******************************************************************************************
// 	**																						**
// 	**	Filename: NegativePriceException.java  												**
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
 * Exception thrown when the pricing of a system asset is set to negative.
 */
public class NegativePriceException extends Exception {
    /**
     * Exception thrown when the pricing of a system asset is set to negative.
     * @param message Error message
     */
    public NegativePriceException(String message) {
        super(message);
    }
}
