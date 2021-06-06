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

public class PasswordChangeException extends Exception {
    /**
     * This UserException class extends the exception class, and is triggered when an error
     * relating to the user is found.
     * @param input String input that holds the message of the error
     */
    public PasswordChangeException(String input){
        super("User exception: " + input);
    }

    public PasswordChangeException(){

    }

    public PasswordChangeException(Throwable cause){
        super(cause);
    }

    public PasswordChangeException(String message, Throwable cause){
        super(message, cause);
    }

    public PasswordChangeException(String message, Throwable cause, boolean suppression, boolean writableStackTrace){
        super(message, cause, suppression, writableStackTrace);
    }
}
