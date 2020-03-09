package by.vorakh.training.my_finance.controller.exception;

public class ControllerException extends Exception {

    private static final long serialVersionUID = -2214429758099463901L;

    public ControllerException(String message, Throwable ex) {
        super(message, ex);
    }

    public ControllerException(String message) {
        super(message);
    }

}
