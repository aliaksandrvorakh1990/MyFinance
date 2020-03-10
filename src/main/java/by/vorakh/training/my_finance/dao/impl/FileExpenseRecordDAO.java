package by.vorakh.training.my_finance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.datasource.AccountDataSource;
import by.vorakh.training.my_finance.dao.datasource.ExpenseRecordDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class FileExpenseRecordDAO implements ExpenseRecordDAO, NotNullValidator {

    private ExpenseRecordDataSource expenseDataSource;
    private AccountDataSource accountDataSource;

    public FileExpenseRecordDAO(ExpenseRecordDataSource expenseDataSource,
            AccountDataSource accountDataSource) {
        this.expenseDataSource = expenseDataSource;
        this.accountDataSource = accountDataSource;
    }

    @Override
    public List<Record> getAll() throws DAOException {
        try {
            String path = accountDataSource.getPathToFile();
            List<Account> allAccounts = new ArrayList<Account>(accountDataSource
                .read(path).values());
            List<Record> allExpenses = new ArrayList<Record>();
            for(Account account : allAccounts) {
            List<Record>  accountExpenses = getAll(account);
            allExpenses.addAll(accountExpenses);
            }
            return allExpenses;
        } catch (DAOException | DataSourceException e) {
            String problem = "[FileExpenseRecordDAO]Unable to execute operation"
                    + " of all records reading:";
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public List<Record> getAll(Account account) throws DAOException {
        String problem = "[FileExpenseRecordDAO]Unable to execute operation"
                + " of reading using account :";
        if (isEqualsNull(account)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String path = expenseDataSource.getPathToFile(account);
            Map<String, Record> expenses = expenseDataSource.read(path);
            List<Record> accountExpenses =
                    new ArrayList<Record>(expenses.values());
            return accountExpenses;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public Record getById(String id) throws DAOException {
        String problem = "[FileExpenseRecordDAO]Unable to execute operation"
                + " of reading using id :";
        if (isEqualsNull(id)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String path = expenseDataSource.getPathToFile(id);
            Map<String, Record> expenses = expenseDataSource.read(path);
            return expenses.get(id);
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(Record object) throws DAOException {
        String problem = "[FileExpenseRecordDAO]Unable to execute creating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String path = expenseDataSource.getPathToFile(object);
            boolean append = true;
            expenseDataSource.write(object, path, append);
            return object.getId();
        }  catch (DataSourceException e) {
            String message = problem + object + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    
    @Override
    public boolean update(Record object) throws DAOException {
        String problem = "[FileExpenseRecordDAO]Unable to execute updating"
                + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Object has null value";
            throw new DAOException(message);
        }
        try {
            String id = object.getId();
            String path = expenseDataSource.getPathToFile(id);
            Map<String, Record> expenses = expenseDataSource.read(path);
            boolean isUpdated = !isEqualsNull(expenses.replace(id, object));
            if (isUpdated) {
                expenseDataSource.clearFile(path);
                boolean append = true;
                expenseDataSource.write(expenses.values(), append);
            }
            return isUpdated;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        String problem = "[FileExpenseRecordDAO]Unable to execute deleting"
                + " operation:";
        if (isEqualsNull(id)) {
            String message = problem + "Id has null value";
            throw new DAOException(message);
        }
        try {
            String path = expenseDataSource.getPathToFile(id);
            Map<String, Record> expenses = expenseDataSource.read(path);
            boolean isDeleted = !isEqualsNull(expenses.remove(id));
            if (isDeleted) {
                expenseDataSource.clearFile(path);
                boolean append = true;
                expenseDataSource.write(expenses.values(), append);
            }
            return isDeleted;
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
            throw new DAOException(message, e);
        }
    }
    
    @Override
    public void delete(Account account) throws DAOException {
    String problem = "[FileExpenseRecordDAO]Unable to execute deleting"
                + " operation:";
        if (isEqualsNull(account)) {
            String message = problem + "Account has null value";
            throw new DAOException(message);
        }
        try {
            String path = expenseDataSource.getPathToFile(account);
            expenseDataSource.delete(path);
        } catch (DataSourceException e) {
            String message = problem + e.getMessage();
                throw new DAOException(message, e);
        }
    }


}
