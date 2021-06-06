package tradingPlatform.exceptions;

public class LoginException extends Exception {
    /**
     * This UserException class extends the exception class, and is triggered when an error
     * relating to the user is found.
     * @param input String input that holds the message of the error
     */
    public  LoginException(String input) {
        super("User exception: " + input);
    }

    public LoginException(){

    }

    public LoginException(Throwable cause){
        super(cause);
    }

    public LoginException(String message, Throwable cause){
        super(message, cause);
    }

    public LoginException(String message, Throwable cause, boolean suppression, boolean writableStackTrace){
        super(message, cause, suppression, writableStackTrace);
    }
}
