package by.vorakh.training.my_finance.convertor.impl.entity;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.validation.bean.RecordValidator;

public class RecordToRecordEntityConvertor implements 
        Convertor<Record, RecordEntity>, RecordValidator {

    @Override
    public RecordEntity converte(Record object) {
        if (!isCorrectForConverting(object)) {
            String message = new StringBuilder(ConvertorException.PROBLEM)
                    .append("Record has null value or one and more "
                            + "fields have null value.")
                    .toString();
            throw new ConvertorException(message);
        }
        return new RecordEntity(object.getId(), object.getAmount(), 
                object.getType());
    }

}
