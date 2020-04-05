package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.RecordValidator.isCorrectForWriting;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class RecordToCsvConvertor implements
        Convertor<Record, String> {

    @Override
    public String converte(Record object) {
        if (!isCorrectForWriting(object)) {
            String message = "RecordEntity has null value or One and more fields "
                    + "have null value.";
            throw new ConvertorException(message);
        }
        return String.join(",", object.getId(), object.getAmount().toString(), 
                object.getType().name());
    }

}
