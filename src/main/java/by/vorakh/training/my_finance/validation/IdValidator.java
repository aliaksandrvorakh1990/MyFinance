package by.vorakh.training.my_finance.validation;

public interface IdValidator extends NotNullValidator {

    default boolean isUserId(String str) {
        String regex = "-?\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }

    default boolean isAccountId(String str) {
        String regex = "-?\\d+A\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }

    default boolean isExpenseRecordId(String str) {
        String regex = "-?\\d+A\\d+T\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }
}
