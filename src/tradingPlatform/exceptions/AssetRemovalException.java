package tradingPlatform.exceptions;

/**
 * Exception thrown when unexpected changes occur when asset is deleted.
 */
public class AssetRemovalException extends Exception {
    /**
     * Exception thrown when unexpected changes occur when asset is deleted.
     * @param message Error message
     */
    public AssetRemovalException(String message) {
        super(message);
    }
}
