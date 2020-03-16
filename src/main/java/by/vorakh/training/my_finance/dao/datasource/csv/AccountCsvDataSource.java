package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;

public abstract class AccountCsvDataSource extends 
        CsvDataSource<Account, Collection<Account>> {

    protected AccountCsvDataSource(
            Convertor<String, Account> csvToEntityConvertor,
            Convertor<Account, String> entityToCsvConvertor) {
        super(csvToEntityConvertor, entityToCsvConvertor);
    }
    
}
