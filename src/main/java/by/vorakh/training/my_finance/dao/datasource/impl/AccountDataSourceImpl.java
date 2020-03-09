package by.vorakh.training.my_finance.dao.datasource.impl;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.AccountDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class AccountDataSourceImpl implements AccountDataSource {

    private static final String PATH = "./src/main/resources/accounts.txt";

    private Convertor<String, Account> stringConvertor;

    private Convertor<Account, String> accountConvertor;

    public AccountDataSourceImpl(Convertor<String, Account> stringConvertor,
            Convertor<Account, String> accountConvertor) {
        this.stringConvertor = stringConvertor;
        this.accountConvertor = accountConvertor;
    }

    @Override
    public void addTo(Map<String, Account> map, Account object) {
        map.put(object.getId(), object);
    }
    @Override
    public String getPathToFile() {
        return PATH;
    }
    @Override
    public Map<String, Account> read(String path) throws
            DataSourceException{
        return this.read(path, stringConvertor);
    }
    @Override
    public void write(Account account, String path, boolean append) throws
            DataSourceException {
        this.write(account, append, path, accountConvertor);
    }
    @Override
    public void write(Collection<Account> accounts, boolean append) throws
            DataSourceException {
        final String PROBLEM = "Unable to execute file writing operation:";
        if (isEqualsNull(accounts)) {
            String message = PROBLEM + "Accounts Collection has null value.";
            throw new DataSourceException(message);
        }
        for (Account account : accounts) {
            try {
                String path = getPathToFile();
                write(account, path, append);
            } catch (DataSourceException e) {
                String message = PROBLEM + e.getMessage();
                throw new DataSourceException(message, e);
            }
        }
    }

}
