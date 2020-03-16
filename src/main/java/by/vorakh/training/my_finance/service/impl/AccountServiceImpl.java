package by.vorakh.training.my_finance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.BeanFillingException;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDao;
    private UserDAO userDAO;
    private RecordDAO expenseDAO;
    private RecordService recordService;
    
    

    public AccountServiceImpl(AccountDAO accountDao, UserDAO userDAO, 
            RecordDAO expenseDAO, RecordService recordService) {
        this.accountDao = accountDao;
        this.userDAO = userDAO;
        this.expenseDAO = expenseDAO;
        this.recordService = recordService;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDao.getAll().stream()
                    .peek(this::fillBean)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public List<Account> getAll(String userId) throws ServiceException {
        if (userId == null) {
            String message = "User ID has null value.\n";
            throw new ServiceException(message);
        }
        try {
            return accountDao.getAll(userId).stream()
                    .peek(this::fillBean)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public Account getById(String id) throws ServiceException {
        if (id == null) {
            String message = "This ID is not Account Id Format.\n";
            throw new ServiceException(message);
        }
        try {
            Account account = accountDao.getById(id);
            if (account != null) {
                fillBean(account);
            }
            return account;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public String create(Account object) throws ServiceException {
        if (object == null) {
            String message = "Account has null value.";
            throw new ServiceException(message);
        }
        try {
            String response = null;
            String userId = object.getId();
            User selectedUser = userDAO.getById(userId);
            long countSameName = accountDao.getAll(userId).stream()
                    .filter(account -> 
                            account.getName().equals(object.getName()))
                    .count();
            if ((selectedUser != null) &&  (countSameName == 0)) {
                long creatingTime = new Date().getTime();
                String accountId = String.format("%s@%s", userId,
                        creatingTime);
                object.setId(accountId);
                String recordId = String.format("%s@%s", accountId,
                        creatingTime);
                Record firstRecord = new Record(recordId,
                        object.getBalance(), ExpenseType.INCOME);
                accountDao.create(object);
                expenseDAO.create(firstRecord);
                response =object.getId();
            }
            return response;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(String id) throws ServiceException {
        if (id == null) {
            String message =  "Account has null value.";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            Account deletedAccount = accountDao.getById(id);
            if (deletedAccount != null) {
                expenseDAO.deleteById(id);
                response = accountDao.delete(id);
            }
            return response;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }
    
    private void fillBean(Account account) {
        try {
            String id = account.getId();
            List<Record> accountRecords = recordService
                    .getAll(id);
            account.setExpenses(accountRecords);
        } catch (ServiceException e) {
            String message = e.getMessage();
            throw new BeanFillingException(message, e);
        }
        
    }

}
