package by.vorakh.training.my_finance.validation.csv;

public interface AccountEntityCsvValidator {
    
    default boolean isCorrectAccountCsv(String csv) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+,[\\w]+,"
                + "(\\d+|\\d+.\\d+)$";
        return ((csv != null) && (csv.matches(regex)));
    }

}
