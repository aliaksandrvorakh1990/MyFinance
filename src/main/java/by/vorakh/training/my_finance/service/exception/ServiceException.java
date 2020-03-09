package by.vorakh.training.my_finance.service.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 5011746039124388054L;

    public ServiceException(String message, Throwable ex) {
        super(message, ex);
    }

    public ServiceException(String message) {
        super(message);
    }

}
