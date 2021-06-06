// 	******************************************************************************************
// 	**																						**
// 	**	Filename: UnitException.java  	    												**
// 	**																						**
// 	**	Description:																		**
// 	**																						**
// 	**																						**
// 	**	Contributors: Nicole Truong															**
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
 * Exception is thrown when unintended discrepancies occur when manipulating a unit object
 */
public class UnitException extends Exception {
    /**
     * Exception is thrown when unintended discrepancies occur when manipulating a unit object
     * @param message Error message
     */
    public UnitException(String message) {
        super(message);
    }
}
