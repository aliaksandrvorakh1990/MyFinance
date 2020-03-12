package by.vorakh.training.my_finance.dao.datasource.exception;

public class DataSourceException extends Exception {

    private static final long serialVersionUID = -5447652578717121296L;
    public final static String READ_PROBLEM = "Unable to execute file reading "
            + "operation:";
    public final static String WRITE_PROBLEM = "Unable to execute file writing"
            + " operation:";
    public final static String CREAR_PROBLEM = "Unable to execute file clear "
            + "operation:";

    public DataSourceException(String message, Throwable ex) {
        super(message, ex);
    }

    public DataSourceException(String message) {
        super(message);
    }

}
