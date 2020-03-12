package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.validation.csv.AccountEntityCsvValidator;

public class CsvToAccountEntityConvertor implements AccountEntityCsvValidator, 
        Convertor<String, AccountEntity> {

    @Override
    public AccountEntity converte(String object) {
        if (!isCorrectAccountCsv(object)) {
            String message = "[CsvToAccountEntityConvertor]" + PROBLEM 
                    + "CVS has a wrong format for account entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String id = values[0];
        String name = values[1];
        BigDecimal balance = new BigDecimal(values[2])
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        return new AccountEntity(id, name, balance);
    }

}
