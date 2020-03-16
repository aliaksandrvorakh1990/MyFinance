package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.AbstractDataSource;

public abstract class UserCsvDataSource extends 
        AbstractDataSource<User, Collection<User>> {

    protected UserCsvDataSource(
            Convertor<String, User> formatToBeanConvertor,
            Convertor<User, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
