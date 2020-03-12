package by.vorakh.training.my_finance.validation;

public interface AccountNameValidator {
    
    default boolean isCorrectAccountName(String str) {
	    String regex ="[\\w]+";
        return ((str != null) && (str.matches(regex)));
    }
}
