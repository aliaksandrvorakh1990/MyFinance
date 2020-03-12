package by.vorakh.training.my_finance.validation.type;

public class PasswordValidator {
    
    private PasswordValidator() {}
    
    public static boolean isCorrectPassword(String password) {
        String regex ="[a-zA-Z\\d]{4,12}?";
        return ((password != null) && (password.matches(regex)));
    }

    public static boolean isEncrytpedPassword(String password) {
        String regex ="[a-f\\d]{63,64}?";
        return ((password != null) && (password.matches(regex)));
    }
    
}
