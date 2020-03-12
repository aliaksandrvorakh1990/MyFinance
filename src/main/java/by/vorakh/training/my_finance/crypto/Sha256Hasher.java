package by.vorakh.training.my_finance.crypto;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.vorakh.training.my_finance.crypto.exception.CryptoException;

public interface Sha256Hasher {

    default String getSHA(String password) throws CryptoException {
        if ((password == null) || (password.isEmpty())) {
            String message =  "Password length is ZERO or has null value.";
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
            String message = e.getMessage();
            throw new CryptoException(message, e);
        }
    }
}
