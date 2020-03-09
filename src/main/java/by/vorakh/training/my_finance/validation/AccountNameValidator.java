package by.vorakh.training.my_finance.validation;

public interface AccountNameValidator extends NotNullValidator {
    
    default boolean isCorrectAccountName(String str) {
	    String regex ="[\\w]+";
        return ((!isEqualsNull(str)) && (str.matches(regex)));
    }
}
