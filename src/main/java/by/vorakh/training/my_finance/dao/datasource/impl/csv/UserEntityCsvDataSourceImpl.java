package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.UserEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class UserEntityCsvDataSourceImpl extends UserEntityCsvDataSource {

    public UserEntityCsvDataSourceImpl(Convertor<String, UserEntity> csvToEntityConvertor,
            Convertor<UserEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

    @Override
    protected void addTo(Map<String, UserEntity> map, UserEntity entity) {
        map.put(entity.getLogin(), entity);
    }

}
