package by.vorakh.training.my_finance.convertor.impl.bean;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.validation.dao_entity.RecordEntityValidator;

public class RecordEntityToRecordConvertor implements 
        Convertor<RecordEntity, Record>, RecordEntityValidator {

    @Override
    public Record converte(RecordEntity object) {
        if (!isCorrectEntity(object)) {
            String message = new StringBuilder(ConvertorException.PROBLEM)
                    .append("RecordEntity has null value or one and more "
                            + "fields have null value.")
                    .toString();
            throw new ConvertorException(message);
        }
        return new Record(object.getId(), object.getAmount(), object.getType());
    }

}
