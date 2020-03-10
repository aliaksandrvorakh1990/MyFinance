package by.vorakh.training.my_finance.validation.csv;

public interface RecordEntityCsvValidator {
    
    default boolean isCorrectRecordCsv(String csv) {
        String regex = "^[a-zA-Z][a-zA-Z\\d]{3,19}?@\\d+@\\d+,(\\d+|\\d+.\\d+),"
                + "[\\w]+$";
        return ((csv != null) && (csv.matches(regex)));
    }

}
