package by.vorakh.training.my_finance.convertor.exception;

public class ConvertorException extends RuntimeException {

    private static final long serialVersionUID = 4398226992589624886L;

    public ConvertorException(String message, Throwable ex) {
        super(message, ex);
    }

    public ConvertorException(String message) {
        super(message);
    }

}
