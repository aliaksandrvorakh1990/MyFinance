package by.vorakh.training.my_finance.dao.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.UserValidator.isCorrectForWriting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvUserDAO implements UserDAO {
    
    private final static String PATH= "./csv/users.csv";
    
    private UserCsvDataSource dataSource;

    public CsvUserDAO(UserCsvDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAll() throws DAOException {
        try {
            return new ArrayList<User>(dataSource.read(PATH).values());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public User getById(String id) throws DAOException {
        if (id == null) {
            String message = "User id has null value.";
            throw new DAOException(message);
        }
        try {
            User user = dataSource.read(PATH).get(id);
            return user;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(User object) throws DAOException {
        if (!isCorrectForWriting(object)) {
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
    public boolean update(User object) throws DAOException {
        if (!isCorrectForWriting(object)) {
            String message = "UserEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            Map<String, User> accounts = dataSource.read(PATH);
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
            Map<String, User> accounts = dataSource.read(PATH);
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
