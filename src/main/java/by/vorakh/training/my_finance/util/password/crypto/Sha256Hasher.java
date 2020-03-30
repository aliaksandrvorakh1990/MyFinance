package by.vorakh.training.my_finance.util.password.crypto;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.vorakh.training.my_finance.util.password.crypto.exception.CryptoException;

public class Sha256Hasher {
    
    private Sha256Hasher() {}

    public static String getSHA(char[] password) throws CryptoException {
        if (password == null) {
            String message = "Unable to execute a password hashing. Password has a null value.";
            throw new CryptoException(message);
        }
        try {
            String passwordAsString = new String(password);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Charset utf8Charset = StandardCharsets.UTF_8;
            byte[] cryptedSymbols = md.digest(passwordAsString.getBytes(utf8Charset));
            BigInteger number = new BigInteger(1, cryptedSymbols);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            String message = "Unable to execute a password hashing." + e.getMessage();
            throw new CryptoException(message, e);
        }
    }

}
