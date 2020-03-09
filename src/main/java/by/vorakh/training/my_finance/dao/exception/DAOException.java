package by.vorakh.training.my_finance.dao.exception;

public class DAOException extends Exception {

    private static final long serialVersionUID = -2643664287757509344L;

    public DAOException(String message, Throwable ex) {
        super(message, ex);
    }

    public DAOException(String message) {
        super(message);
    }

}
