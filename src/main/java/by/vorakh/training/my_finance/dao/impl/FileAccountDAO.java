package by.vorakh.training.my_finance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.datasource.AccountDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class FileAccountDAO implements AccountDAO , NotNullValidator{

    private AccountDataSource accountDataSource;
    private ExpenseRecordDAO expenseDAO;

    public FileAccountDAO(AccountDataSource accountDataSource, ExpenseRecordDAO expenseDAO) {
        this.accountDataSource = accountDataSource;
        this.expenseDAO = expenseDAO;
    }

    @Override
    public List<Account> getAll() throws DAOException {
        try {
            String path = accountDataSource.getPathToFile();
            Map<String, Account> accountsMap = accountDataSource.read(path);
            for (Account account : accountsMap.values()) {
                List<Record> accountExpenses = expenseDAO.getAll(account);
                account.setExpenses(accountExpenses);
            }
            List<Account> allAccounts = new ArrayList<Account>(accountsMap.values());
            return allAccounts;
        } catch (DataSourceException | DAOException e) {
            String problem = "[FileAccountDAO]Unable to execute operation"
                    + " of all records reading:";
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public List<Account> getAll(String userId) throws DAOException {
    String problem = "[FileAccountDAO]Unable to execute operation"
                + " of reading using user :";
        if (isEqualsNull(userId)) {
            String message = problem + "User ID has null value";
            throw new DAOException(message);
        }
        try {
            String path = accountDataSource.getPathToFile();
            Map<String, Account> accountsMap = accountDataSource.read(path);
            List<Account> allUserAccounts = new ArrayList<Account>();
            for (Account account : accountsMap.values()) {
                String accountId = account.getId();
                if (accountId.startsWith(userId)) {
                    List<Record> accountExpenses = expenseDAO.getAll(account);
                    account.setExpenses(accountExpenses);
                    allUserAccounts.add(account);
                }
            }
            return allUserAccounts;
        } catch (DataSourceException | DAOException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public Account getById(String id) throws DAOException {
        String problem = "[FileAccountDAO]Unable to execute operation"
                + " of reading using id :";
        if (isEqualsNull(id)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String path = accountDataSource.getPathToFile();
            Map<String, Account> accountsMap = accountDataSource.read(path);
            Account account = accountsMap.get(id);
            if (!isEqualsNull(account)) {
                List<Record> accountExpenses = expenseDAO.getAll(account);
                account.setExpenses(accountExpenses);
            }
            return account;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(Account object) throws DAOException {
        String problem = "[FileAccountDAO]Unable to execute creating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String path = accountDataSource.getPathToFile();
            boolean append = true;
            accountDataSource.write(object, path, append);
            return object.getId();
        }  catch (DataSourceException e) {
            String message = problem + object + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean update(Account object) throws DAOException {
        String problem = "[FileAccountDAO]Unable to execute updating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String id = object.getId();
            String path = accountDataSource.getPathToFile();
            Map<String, Account> accountsMap = accountDataSource.read(path);
            boolean isUpdated = !isEqualsNull(accountsMap.replace(id, object));
            if (isUpdated) {
                accountDataSource.clearFile(path);
                boolean append = true;
                accountDataSource.write(accountsMap.values(), append);
            }
            return isUpdated;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        String problem = "[FileAccountDAO]Unable to execute deleting"
                + " operation:";
        if (isEqualsNull(id)) {
            String message = problem + "Id has null value";
            throw new DAOException(message);
        }
        try {
            String path = accountDataSource.getPathToFile();
            Map<String, Account> accountsMap = accountDataSource.read(path);
            Account deletedAccount = accountsMap.remove(id);
            boolean isDeleted = !isEqualsNull(deletedAccount);
            if (isDeleted) {
                expenseDAO.delete(deletedAccount);
                accountDataSource.clearFile(path);
                boolean append = true;
                accountDataSource.write(accountsMap.values(), append);
            }
            return isDeleted;
        } catch (DataSourceException | DAOException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }



}
