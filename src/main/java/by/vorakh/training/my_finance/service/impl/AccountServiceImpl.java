package by.vorakh.training.my_finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.CurrencyValidator;
import by.vorakh.training.my_finance.validation.IdValidator;

public class AccountServiceImpl implements AccountService, IdValidator,
        CurrencyValidator {

    private AccountDAO accountDao;
    private UserDAO userDAO;
    private ExpenseRecordDAO expenseDAO;

    public AccountServiceImpl(AccountDAO accountDao, UserDAO userDAO,
            ExpenseRecordDAO expenseDAO) {
        this.accountDao = accountDao;
        this.userDAO = userDAO;
        this.expenseDAO = expenseDAO;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute operation"
                + " of all accounts reading:";
        try {
            return accountDao.getAll();
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public List<Account> getAll(String userId) throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute operation"
                + " of accounts reading using user id:";
        if (isEqualsNull(userId)) {
            String message = problem + "User ID has null value.\n";
            throw new ServiceException(message);
        }
        if (!isUserId(userId)) {
            String message = problem + "This ID is not User Id Format.\n";
            throw new ServiceException(message);
        }
        try {
            User user = userDAO.getById(Integer.valueOf(userId));
            List<Account> userAccounts = new ArrayList<Account>();
            if (!isEqualsNull(user)) {
                userAccounts.addAll(accountDao.getAll(userId));
            }
            return userAccounts;
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public Account getById(String id) throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute operation"
                + " of accounts reading using account id:";
        if (isEqualsNull(id)) {
            String message = problem + "Account ID has null value.\n";
            throw new ServiceException(message);
        }
        if (!isAccountId(id)) {
            String message = problem + "This ID is not Account Id Format.\n";
            throw new ServiceException(message);
        }
        try {
            return accountDao.getById(id);
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public String create(Account object) throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute account creating "
                + "operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Account has null value.";
            throw new ServiceException(message);
        }
        String userId = object.getId();
        if (!isUserId(userId)) {
            String message = problem + "This is not user id.";
            throw new ServiceException(message);
        }
        try {
            String response = null;
            User selecttedUser = userDAO.getById(Integer.valueOf(userId));
            if (!isEqualsNull(selecttedUser)) {
                long creatingTime = new Date().getTime();
                String accountId = String.format("%sA%s", userId,
                        creatingTime);
                if (!isAccountId(accountId)) {
                    String message = problem + "This id has wrong format.";
                    throw new ServiceException(message);
                }
                object.setId(accountId);
                String recordId = String.format("%sT%s", accountId,
                        creatingTime);
                ExpenseRecord firstRecord = new ExpenseRecord(recordId,
                        object.getBalance(), ExpenseType.INCOME);
                accountDao.create(object);
                expenseDAO.create(firstRecord);
                response =object.getId();
            }
            return response;
        } catch (DAOException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean update(Account object) throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute account updating"
            + " operation:";
        if (isEqualsNull(object)) {
            String message = problem + "Account has null value.";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            String id = object.getId();
            Account oldAccount = getById(id);
            if (isEqualsNull(oldAccount)) {
                String message = problem + "Account does not exist.";
                throw new ServiceException(message);
            }
            BigDecimal balance = object.getBalance();
            if (!isCorrectValue(balance)) {
                String message = problem + "balance has bad value.";
                throw new ServiceException(message);
            }
            response = accountDao.update(object);
            return response;
        } catch (DAOException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(String id) throws ServiceException {
        String problem = "[AccountServiceImpl]Unable to execute account deleting"
                + " operation:";
        if (isEqualsNull(id)) {
            String message = problem + "Account has null value.";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            Account deletedAccount = getById(id);
            if (!isEqualsNull(deletedAccount)) {
                response = accountDao.delete(id);
            }
            return response;
        } catch (DAOException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

}
