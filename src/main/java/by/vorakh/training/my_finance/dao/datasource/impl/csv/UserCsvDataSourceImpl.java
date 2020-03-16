package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;

public class UserCsvDataSourceImpl extends UserCsvDataSource {

    public UserCsvDataSourceImpl(
            Convertor<String, User> formatToBeanConvertor,
            Convertor<User, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

    @Override
    protected void addTo(Map<String, User> map, User entity) {
        map.put(entity.getLogin(), entity);
    }

}
