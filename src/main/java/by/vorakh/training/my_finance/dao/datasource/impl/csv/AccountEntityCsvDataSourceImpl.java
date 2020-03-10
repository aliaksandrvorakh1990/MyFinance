package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityCsvDataSourceImpl extends AccountEntityCsvDataSource {

    public AccountEntityCsvDataSourceImpl(
            Convertor<String, AccountEntity> csvToEntityConvertor,
            Convertor<AccountEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

    @Override
    protected void addTo(Map<String, AccountEntity> map, AccountEntity entity) {
        map.put(entity.getId(), entity);
    }

}
