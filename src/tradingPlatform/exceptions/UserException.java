// 	******************************************************************************************
// 	**																						**
// 	**	Filename: UserException.java														**
// 	**																						**
// 	**	Description: User Exception class for User											**
// 	**																						**
// 	**																						**
// 	**	Contributors: Natalie Smith										                    **
// 	**																						**
// 	**																						**
// 	**	Date Created: 															            **
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**																						**
// 	**																						**
// 	******************************************************************************************
package tradingPlatform.exceptions;

public class UserException extends Exception {
    /**
     * This UserException class extends the exception class, and is triggered when an error
     * relating to the user is found.
     * @param input String input that holds the message of the error
     */
    public  UserException(String input){
        super("User exception: " + input);
    }
}
