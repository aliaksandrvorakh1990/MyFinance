package by.vorakh.training.my_finance.validation;

public interface IdValidator extends NotNullValidator {

    default boolean isAccountId(String str) {
        String regex = "-?\\d+@\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }

    default boolean isExpenseRecordId(String str) {
        String regex = "-?\\d+@\\d+T\\d+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }
}
