package by.vorakh.training.my_finance.validation.csv;

public class UserEntityCsvValidator {

    private UserEntityCsvValidator() {}
    
    public static boolean isCorrectUserCsv(String csv) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?,[a-f\\d]{63,64}?,"
                + "[A-Z]{4,15}?$";
        return ((csv != null) && (csv.matches(regex)));
    }
}
