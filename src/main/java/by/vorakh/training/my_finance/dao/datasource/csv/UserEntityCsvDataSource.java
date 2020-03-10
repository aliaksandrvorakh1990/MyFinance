package by.vorakh.training.my_finance.dao.datasource.csv;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public abstract class UserEntityCsvDataSource extends CsvDataSource<UserEntity> {

    protected UserEntityCsvDataSource(Convertor<String, UserEntity> csvToEntityConvertor,
            Convertor<UserEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

}
