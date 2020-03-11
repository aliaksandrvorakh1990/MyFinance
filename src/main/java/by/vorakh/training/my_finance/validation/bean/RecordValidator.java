package by.vorakh.training.my_finance.validation.bean;

import by.vorakh.training.my_finance.bean.Record;

public interface RecordValidator {
    
    default boolean isCorrectForConverting(Record record) {
        return ((record != null) && ((record.getId() != null) 
                && (record.getAmount() != null) 
                && (record.getType() != null)));
    }

}
