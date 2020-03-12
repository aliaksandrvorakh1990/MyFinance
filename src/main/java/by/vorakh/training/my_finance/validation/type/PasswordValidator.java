package by.vorakh.training.my_finance.validation.type;

public interface PasswordValidator {
    
    default boolean isCorrectPassword(String password) {
        String regex ="[a-zA-Z\\d]{4,12}?";
        return ((password != null) && (password.matches(regex)));
    }

    default boolean isEncrytpedPassword(String password) {
        String regex ="[a-f\\d]{63,64}?";
        return ((password != null) && (password.matches(regex)));
    }
    
}
