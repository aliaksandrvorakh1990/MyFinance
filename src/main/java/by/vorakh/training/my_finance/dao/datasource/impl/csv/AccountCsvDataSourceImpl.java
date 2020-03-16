package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;

public class AccountCsvDataSourceImpl extends AccountCsvDataSource {

    public AccountCsvDataSourceImpl(
            Convertor<String, Account> csvToEntityConvertor,
            Convertor<Account, String> entityToCsvConvertor) {
        super(csvToEntityConvertor, entityToCsvConvertor);
    }

    @Override
    protected void addTo(Map<String, Account> map, Account entity) {
        map.put(entity.getId(), entity);
    }

}
