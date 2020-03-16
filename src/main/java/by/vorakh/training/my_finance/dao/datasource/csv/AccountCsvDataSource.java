package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.AbstractDataSource;

public abstract class AccountCsvDataSource extends 
        AbstractDataSource<Account, Collection<Account>> {

    protected AccountCsvDataSource(
            Convertor<String, Account> formatToBeanConvertor,
            Convertor<Account, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
