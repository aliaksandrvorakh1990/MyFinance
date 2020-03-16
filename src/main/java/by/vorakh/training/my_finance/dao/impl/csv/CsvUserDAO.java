package by.vorakh.training.my_finance.dao.impl.csv;

import static by.vorakh.training.my_finance.validation.dao_entity.UserEntityValidator.isCorrectEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvUserDAO implements UserDAO {
    
    private final static String PATH= "./csv/users.csv";
    
    private UserCsvDataSource dataSource;

    public CsvUserDAO(UserCsvDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<UserEntity> getAll() throws DAOException {
        try {
            return new ArrayList<UserEntity>(dataSource.read(PATH).values());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public UserEntity getById(String id) throws DAOException {
        if (id == null) {
            String message = "User id has null value.";
            throw new DAOException(message);
        }
        try {
            UserEntity user = dataSource.read(PATH).get(id);
            return user;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(UserEntity object) throws DAOException {
        if (!isCorrectEntity(object)) {
            String message = "UserEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            dataSource.write(object, PATH);
            return object.getLogin();
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean update(UserEntity object) throws DAOException {
        if (!isCorrectEntity(object)) {
            String message = "UserEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, UserEntity> accounts = dataSource.read(PATH);
            String id = object.getLogin();
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
            String message = "User id has null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, UserEntity> accounts = dataSource.read(PATH);
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
