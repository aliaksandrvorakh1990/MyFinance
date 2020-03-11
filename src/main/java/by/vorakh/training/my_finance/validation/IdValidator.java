package by.vorakh.training.my_finance.validation;

public interface IdValidator extends NotNullValidator {

    default boolean isAccountId(String str) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }

    default boolean isRecordId(String str) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+@\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }
}
