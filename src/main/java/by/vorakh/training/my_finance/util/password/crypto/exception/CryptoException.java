package by.vorakh.training.my_finance.util.password.crypto.exception;

public class CryptoException extends Exception {

    private static final long serialVersionUID = 5435139188675010573L;

    public CryptoException(String message, Throwable ex) {
        super(message, ex);
    }

    public CryptoException(String message) {
         super(message);
    }

}
