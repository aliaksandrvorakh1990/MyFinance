package by.vorakh.training.my_finance.service.exception;

public class BeanFillingException extends RuntimeException {

    private static final long serialVersionUID = -7334839265345250382L;

    public BeanFillingException(String message, Throwable e) {
        super(message, e);
    }

    public BeanFillingException(String message) {
        super(message);
    }

}
