package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.csv.RecordEntityCsvValidator.isCorrectRecordCsv;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToRecordConvertor implements
        Convertor<String, Record> {

    @Override
    public Record converte(String object) {
        if (!isCorrectRecordCsv(object)) {
            String message = "CVS has a wrong format for account entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String id = values[0];
        BigDecimal amount = new BigDecimal(values[1]).setScale(2);
        ExpenseType type = ExpenseType.valueOf(values[2]);
        return new Record(id, amount, type);
    }

}
