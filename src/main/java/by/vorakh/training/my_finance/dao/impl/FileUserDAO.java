package by.vorakh.training.my_finance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.datasource.UserDataSource;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class FileUserDAO implements UserDAO, NotNullValidator {

    private UserDataSource userDataSource;
    private AccountDAO accountDAO;

    public FileUserDAO(UserDataSource userDataSource, AccountDAO accountDAO) {
        this.userDataSource = userDataSource;
        this.accountDAO = accountDAO;
    }

    public List<User> getAll() throws DAOException {
        try {
            String path = userDataSource.getPathToFile();
            Map<String, User> users; users = userDataSource.read(path);
            for (User user : users.values()) {
            String userID = user.getLogin();
                List<Account> userAccounts = accountDAO.getAll(userID);
                user.setAccounts(userAccounts);
            }
            List<User> userList = new ArrayList<User>(users.values());
            return userList;
        } catch (DataSourceException e) {
            String problem = "[FileUserDAO]Unable to execute operation"
                    + " of all records reading:";
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    public User getById(String id) throws DAOException {
        String problem = "[FileUserDAO]Unable to execute operation"
                + " of reading using id:";
        if (isEqualsNull(id)) {
            String message = problem + "Object has null value.\n";
            throw new DAOException(message);
        }
        try {
            String path = userDataSource.getPathToFile();
            Map<String, User> users = userDataSource.read(path);
            User user = users.get(id);
            if (!isEqualsNull(user)) {
                List<Account> userAccounts = accountDAO.getAll(id.toString());
                user.setAccounts(userAccounts);
            }
            return user;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    public String create(User object) throws DAOException {
        String problem = "[FileUserDAO]Unable to execute creating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value.\n";
            throw new DAOException(message);
        }
        try {
            String path = userDataSource.getPathToFile();
            boolean append = true;
            userDataSource.write(object, path, append);
            return object.getLogin();
        }  catch (DataSourceException e) {
            String message = problem + object + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean update(User object) throws DAOException {
        String problem = "[FileUserDAO]Unable to execute updating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value.\n";
            throw new DAOException(message);
        }
        try {
            String id = object.getLogin();
            String path = userDataSource.getPathToFile();
            Map<String, User> usersMap = userDataSource.read(path);
            boolean isUpdated = !isEqualsNull(usersMap.replace(id, object));
            if (isUpdated) {
                userDataSource.clearFile(path);
                boolean append = true;
                userDataSource.write(usersMap.values(), append);
            }
            return isUpdated;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        String problem = "[FileUserDAO]Unable to execute deleting"
                + " operation:";
        if (isEqualsNull(id)) {
            String message = problem + "ID has null value.\n";
            throw new DAOException(message);
        }
        try {
            String path = userDataSource.getPathToFile();
            Map<String, User> usersMap = userDataSource.read(path);
            boolean isDeleted = !isEqualsNull(usersMap.remove(id));
            if (isDeleted) {
                userDataSource.clearFile(path);
                boolean append = true;
                userDataSource.write(usersMap.values(), append);
            }
            return isDeleted;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

}
