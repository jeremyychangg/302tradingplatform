package tradingPlatform.exceptions;

public class InvalidOrderException extends Throwable {
    public InvalidOrderException(String message) {
        super(message);
    }
}
