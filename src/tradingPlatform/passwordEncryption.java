package tradingPlatform;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class passwordEncryption {
    private Optional<String> passwordEncrypt;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public passwordEncryption(String password, String salt){
        this.passwordEncrypt = hashPassword(password, salt);
    }
    private static final SecureRandom random = new SecureRandom();
    public static Optional<String> generateSalt (final int length) {
        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        random.nextBytes(salt);
        System.out.println(Optional.of(Base64.getEncoder().encodeToString(salt)));
        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }



    public static Optional<String> hashPassword (String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }

    public static void main(String[] args) throws Exception {
        String salt = generateSalt(100).get();
        String password = "Hello!";

        String key = hashPassword(password, salt).get();
        salt = generateSalt(100).get();
        System.out.println(key);
        System.out.println(verifyPassword("Hello!", key, salt));
    }
}


