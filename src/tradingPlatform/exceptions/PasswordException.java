package tradingPlatform.exceptions;

public class PasswordException extends Exception {
    /**
     * This PasswordException class extends the exception class, and is triggered when an error
     * relating to the user is found.
     * @param input String input that holds the message of the error
     */
    public  PasswordException(String input){
        super("Password exception: " + input);
    }
}
