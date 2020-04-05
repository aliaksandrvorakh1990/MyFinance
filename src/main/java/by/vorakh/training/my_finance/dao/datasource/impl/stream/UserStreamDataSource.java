package by.vorakh.training.my_finance.dao.datasource.impl.stream;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.StreamDataSource;

public class UserStreamDataSource extends StreamDataSource<User> {

    public UserStreamDataSource(Convertor<String, User> formatToBeanConvertor,
            Convertor<User, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
