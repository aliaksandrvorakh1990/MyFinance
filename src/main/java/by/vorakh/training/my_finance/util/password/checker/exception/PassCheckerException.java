package by.vorakh.training.my_finance.util.password.checker.exception;

public class PassCheckerException extends Exception {

    private static final long serialVersionUID = 5934889707880729694L;

    public PassCheckerException(String message) {
        super(message);
    }

    public PassCheckerException(String message, Throwable ex) {
        super(message, ex);
    }

}
