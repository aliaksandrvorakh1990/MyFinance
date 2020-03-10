package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.validation.csv.RecordEntityCsvValidator;

public class CsvToRecordEntityConvertor implements RecordEntityCsvValidator, Convertor<String, RecordEntity> {

    @Override
    public RecordEntity converte(String object) throws ConvertorException {
        if (!isCorrectRecordCsv(object)) {
            String message = "[CsvToAccountEntityConvertor]" + PROBLEM 
                    + "CVS has a wrong format for account entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String id = values[0];
        BigDecimal amount = new BigDecimal(values[1])
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.valueOf(values[2]);
        return new RecordEntity(id, amount, type);
    }

}
