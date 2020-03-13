package by.vorakh.training.my_finance.dao.datasource.exception;

public class DataSourceException extends Exception {

    private static final long serialVersionUID = -5447652578717121296L;
    
    public DataSourceException(String message, Throwable ex) {
        super(message, ex);
    }

    public DataSourceException(String message) {
        super(message);
    }

}
