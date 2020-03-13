package by.vorakh.training.my_finance.convertor.impl.bean;

import static by.vorakh.training.my_finance.validation.dao_entity.RecordEntityValidator.isCorrectEntity;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityToRecordConvertor implements 
        Convertor<RecordEntity, Record> {

    @Override
    public Record converte(RecordEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "RecordEntity has null value or one and more "
                    + "fields have null value.";
            throw new ConvertorException(message);
        }
        return new Record(object.getId(), object.getAmount(), object.getType());
    }

}
