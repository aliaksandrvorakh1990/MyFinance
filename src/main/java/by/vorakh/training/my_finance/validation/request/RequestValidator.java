package by.vorakh.training.my_finance.validation.request;

public class RequestValidator {
    
    private RequestValidator() {}

    public static boolean isCorrect(String request) {
        String regex = "[A-Z_]{4,}?#[@.&=\\w]*";
        return ((request != null) && (request.matches(regex)));
    }

    public static boolean isCorrectRequestWithoutArgs(String request) {
        String regex ="[A-Z_]{4,}?#";
        return ((request != null) && (request.matches(regex)));
    }

    public static boolean isSingleArgRequest(String request) {
        String regex ="^[A-Z_]{2,}?=[.\\w@]+$";
        return ((request != null) && (request.matches(regex)));
    }

    public static boolean isMultiArgsRequest(String request) {
        String regex ="^[A-Z_]{2,}?=[@.\\w]+(&[A-Z_]{2,}?=[\\w.]+){1,}?$";
        return ((request != null) && (request.matches(regex)));
    }

}
