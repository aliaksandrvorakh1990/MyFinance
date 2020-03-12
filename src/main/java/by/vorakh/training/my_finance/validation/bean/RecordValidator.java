package by.vorakh.training.my_finance.validation.bean;

import by.vorakh.training.my_finance.bean.Record;

public class RecordValidator {
    
    private RecordValidator() {}
    
    public static boolean isCorrectForConverting(Record record) {
        return ((record != null) && ((record.getId() != null) 
                && (record.getAmount() != null) 
                && (record.getType() != null)));
    }

}
