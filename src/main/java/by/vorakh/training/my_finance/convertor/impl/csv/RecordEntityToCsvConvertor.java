package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.validation.dao_entity.RecordEntityValidator;

public class RecordEntityToCsvConvertor implements RecordEntityValidator, 
        Convertor<RecordEntity, String> {

    @Override
    public String converte(RecordEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "[RecordEntityToCsvConvertor]" + PROBLEM  
                    + "RecordEntity has null value or One and more fields "
                    + "have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getId(), 
                object.getAmount(), object.getType());
    }

}
