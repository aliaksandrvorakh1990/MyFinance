package by.vorakh.training.my_finance.validation;

public interface NotNullValidator {

    default boolean isEqualsNull(Object obj) {
        return (obj == null);
    }
}
