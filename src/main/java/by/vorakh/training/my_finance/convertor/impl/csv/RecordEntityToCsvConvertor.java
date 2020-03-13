package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.dao_entity.RecordEntityValidator.isCorrectEntity;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityToCsvConvertor implements
        Convertor<RecordEntity, String> {

    @Override
    public String converte(RecordEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "RecordEntity has null value or One and more fields "
                    + "have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getId(), 
                object.getAmount(), object.getType());
    }

}
