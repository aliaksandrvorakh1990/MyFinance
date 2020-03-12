package by.vorakh.training.my_finance.validation.type;

public class IdValidator {
    
    private IdValidator() {}

    public static boolean isAccountId(String str) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+";
        return ((str != null) && (str.matches(regex)));
    }

    public static boolean isRecordId(String str) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+@\\d+";
        return ((str != null) && (str.matches(regex)));
    }
}
