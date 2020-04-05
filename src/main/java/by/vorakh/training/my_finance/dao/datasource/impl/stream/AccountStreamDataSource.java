package by.vorakh.training.my_finance.dao.datasource.impl.stream;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.StreamDataSource;

public class AccountStreamDataSource extends StreamDataSource<Account> {

    public AccountStreamDataSource(
            Convertor<String, Account> formatToBeanConvertor,
            Convertor<Account, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
