package by.vorakh.training.my_finance.dao.impl.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.validation.dao_entity.AccountEntityValidator;

public class CsvAccountDAO implements AccountDAO, AccountEntityValidator {
    
    private final static String PATH= "src/main/resources/csv/accounts.csv";
    
    private AccountEntityCsvDataSource dataSource;

    public CsvAccountDAO(AccountEntityCsvDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AccountEntity> getAll() throws DAOException {
        try {
            return new ArrayList<AccountEntity>(dataSource.read(PATH).values());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }
    
    @Override
    public List<AccountEntity> getAll(String userId) throws DAOException {
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
    public AccountEntity getById(String id) throws DAOException {
        if (id == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        try {
            AccountEntity account = dataSource.read(PATH).get(id);
            return account;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(AccountEntity object) throws DAOException {
        if (!isCorrectEntity(object)) {
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
    public boolean update(AccountEntity object) throws DAOException {
        if (!isCorrectEntity(object)) {
            String message = "AccountEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, AccountEntity> accounts = dataSource.read(PATH);
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
            Map<String, AccountEntity> accounts = dataSource.read(PATH);
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
