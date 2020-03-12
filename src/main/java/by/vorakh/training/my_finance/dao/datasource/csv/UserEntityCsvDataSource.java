package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public abstract class UserEntityCsvDataSource extends 
        CsvDataSource<UserEntity, Collection<UserEntity>> {

    protected UserEntityCsvDataSource(
            Convertor<String, UserEntity> csvToEntityConvertor,
            Convertor<UserEntity, String> entityToCsvConvertor) {
        super(csvToEntityConvertor, entityToCsvConvertor);
    }
    
}
