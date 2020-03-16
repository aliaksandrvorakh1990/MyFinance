package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.csv.AccountEntityCsvValidator.isCorrectAccountCsv;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToAccountConvertor implements  
        Convertor<String, Account> {

    @Override
    public Account converte(String object) {
        if (!isCorrectAccountCsv(object)) {
            String message = "CVS has a wrong format for account entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String id = values[0];
        String name = values[1];
        BigDecimal balance = new BigDecimal(values[2])
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        return new Account(id, name, balance);
    }

}
