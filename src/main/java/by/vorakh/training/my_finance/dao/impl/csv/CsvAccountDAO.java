package by.vorakh.training.my_finance.dao.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.AccountValidator.isCorrectForWriting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvAccountDAO implements AccountDAO {
    
    private final static String PATH= "./csv/accounts.csv";
    
    private AccountCsvDataSource dataSource;

    public CsvAccountDAO(AccountCsvDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Account> getAll() throws DAOException {
        try {
            return new ArrayList<Account>(dataSource.read(PATH).values());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }
    
    @Override
    public List<Account> getAll(String userId) throws DAOException {
        if (userId == null) {
            String message = "User id has null value.";
            throw new DAOException(message);
        }
        try {
            return dataSource.read(PATH).values()
                    .stream()
                    .filter(account -> account.getId().startsWith(userId))
                    .collect(Collectors.toList());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public Account getById(String id) throws DAOException {
        if (id == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        try {
            Account account = dataSource.read(PATH).get(id);
            return account;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(Account object) throws DAOException {
        if (!isCorrectForWriting(object)) {
            String message = "AccountEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            dataSource.write(object, PATH);
            return object.getId();
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean update(Account object) throws DAOException {
        if (!isCorrectForWriting(object)) {
            String message = "AccountEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, Account> accounts = dataSource.read(PATH);
            String id = object.getId();
            boolean isUpdated = (accounts.replace(id, object) != null);
            if (isUpdated) {
                dataSource.clearFile(PATH);
                dataSource.write(accounts.values(), PATH);
            }
            return isUpdated;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        if (id == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, Account> accounts = dataSource.read(PATH);
            boolean isDeleted = (accounts.remove(id) != null);
            if (isDeleted) {
                dataSource.clearFile(PATH);
                dataSource.write(accounts.values(), PATH);
            }
            return isDeleted;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

}
