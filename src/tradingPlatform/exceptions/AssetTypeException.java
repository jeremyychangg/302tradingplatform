package tradingPlatform.exceptions;

/**
 * Exception called when there's an error with the asset's type being invalid with existing records
 */
public class AssetTypeException extends Exception {
    /**
     * Exception called when there's an error with the asset's type being invalid with existing records
     * @param message Error message
     */
    public AssetTypeException(String message) {
        super(message);
    }
}
