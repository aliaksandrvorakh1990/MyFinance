package by.vorakh.training.my_finance.dao.datasource.csv;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public abstract class AccountEntityCsvDataSource extends CsvDataSource<AccountEntity> {

    protected AccountEntityCsvDataSource(
            Convertor<String, AccountEntity> csvToEntityConvertor,
            Convertor<AccountEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

}
