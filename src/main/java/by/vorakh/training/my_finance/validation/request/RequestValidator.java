package by.vorakh.training.my_finance.validation.request;

public interface RequestValidator {

    default boolean isCorrect(String request) {
        String regex = "[A-Z_]{4,}?#[@.&=\\w]*";
        return ((request != null) && (request.matches(regex)));
    }

    default boolean isCorrectRequestWithoutArgs(String request) {
        String regex ="[A-Z_]{4,}?#";
        return ((request != null) && (request.matches(regex)));
    }

    default boolean isSingleArgRequest(String request) {
        String regex ="^[A-Z_]{2,}?=[.\\w@]+$";
        return ((request != null) && (request.matches(regex)));
    }

    default boolean isMultiArgsRequest(String request) {
        String regex ="^[A-Z_]{2,}?=[@.\\w]+(&[A-Z_]{2,}?=[\\w.]+){1,}?$";
        return ((request != null) && (request.matches(regex)));
    }

}
