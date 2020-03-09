package by.vorakh.training.my_finance.crypto;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.vorakh.training.my_finance.crypto.exception.CryptoException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public interface Sha256Hasher extends NotNullValidator {

    default String getSHA(String password) throws CryptoException {
        String problem = "[Sha256Hasher]Unable to execute encrypting operation:";
        if (isEqualsNull(password)) {
            String message = problem + "Password has null value.";
            throw new CryptoException(message);
        }
        if (password.isEmpty()) {
            String message = problem + "Password length is ZERO.";
            throw new CryptoException(message);
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Charset utf8Charset = StandardCharsets.UTF_8;
            byte[] cryptedSymbols = md.digest(password.getBytes(utf8Charset));
            BigInteger number = new BigInteger(1, cryptedSymbols);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            String message = problem + e.getMessage();
            throw new CryptoException(message, e);
        }
    }
}
