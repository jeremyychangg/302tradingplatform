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

public class EditUserException extends Exception {
    /**
     * This EditUserException class extends the exception class, and is triggered when an error
     * relating to the user is found.
     * @param input String input that holds the message of the error
     */
    public EditUserException(String input){
        super("Edit User exception: " + input);
    }

    public EditUserException(){

    }

    public EditUserException(Throwable cause){
        super(cause);
    }

    public EditUserException(String message, Throwable cause){
        super(message, cause);
    }

    public EditUserException(String message, Throwable cause, boolean suppression, boolean writableStackTrace){
        super(message, cause, suppression, writableStackTrace);
    }
}
