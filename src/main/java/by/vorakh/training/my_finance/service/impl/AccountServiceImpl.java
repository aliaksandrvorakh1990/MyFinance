package by.vorakh.training.my_finance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.BeanFillingException;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.type.CurrencyValidator;
import by.vorakh.training.my_finance.validation.type.IdValidator;

public class AccountServiceImpl implements AccountService, IdValidator,
        CurrencyValidator {

    private AccountDAO accountDao;
    private UserDAO userDAO;
    private RecordDAO expenseDAO;
    private RecordService recordService;
    private Convertor<AccountEntity, Account> entityConvertor;
    private Convertor<Account, AccountEntity> beanConvertor;

    public AccountServiceImpl(AccountDAO accountDao, UserDAO userDAO, 
            RecordDAO expenseDAO, RecordService recordService,
            Convertor<AccountEntity, Account> entityConvertor, 
            Convertor<Account, AccountEntity> beanConvertor) {
        this.accountDao = accountDao;
        this.userDAO = userDAO;
        this.expenseDAO = expenseDAO;
        this.recordService = recordService;
        this.entityConvertor = entityConvertor;
        this.beanConvertor = beanConvertor;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDao.getAll().stream()
                    .map(this::fillBean).collect(Collectors.toList());
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
            return accountDao.getAll(userId).stream().collect(
                    Collectors.mapping(accountEntity -> fillBean(accountEntity),
                    Collectors.toList()));
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
            Account foundAccount = null;
            AccountEntity account = accountDao.getById(id);
            if (account != null) {
                foundAccount = fillBean(account);
            }
            return foundAccount;
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
            UserEntity selectedUser = userDAO.getById(userId);
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
                RecordEntity firstRecord = new RecordEntity(recordId,
                        object.getBalance(), ExpenseType.INCOME);
                accountDao.create(beanConvertor.converte(object));
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
            AccountEntity deletedAccount = accountDao.getById(id);
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
    
    private Account fillBean(AccountEntity entity) {
        try {
            Account account = entityConvertor.converte(entity);
            String id = account.getId();
            List<Record> accountRecords = recordService
                    .getAll(id);
            account.setExpenses(accountRecords);
            return account;
        } catch (ServiceException e) {
            String message = e.getMessage();
            throw new BeanFillingException(message, e);
        }
        
    }

}
