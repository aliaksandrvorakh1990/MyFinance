package by.vorakh.training.my_finance.service.factory;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.bean.AccountEntityToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.bean.RecordEntityToRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.bean.UserEntityToUserConvertor;
import by.vorakh.training.my_finance.convertor.impl.entity.AccountToAccountEntityConvertor;
import by.vorakh.training.my_finance.convertor.impl.entity.RecordToRecordEntityConvertor;
import by.vorakh.training.my_finance.convertor.impl.entity.UserToUserEntityConvertor;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.dao.factory.DaoFactory;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.impl.AccountServiceImpl;
import by.vorakh.training.my_finance.service.impl.RecordServiceImpl;
import by.vorakh.training.my_finance.service.impl.UserServiceImpl;

public class ServiceFactory {

    private UserService userService;
    private AccountService accountService;
    private RecordService recordService;
    
    public ServiceFactory() {
        setRecordService(new RecordServiceBuilder()
                .setAccountDAO(new DaoFactory().getAccountDAO())
                .setExpenseDAO(new DaoFactory().getRecordDAO())
                .setEntityConvertor(new RecordEntityToRecordConvertor())
                .setBeanConvertor(new RecordToRecordEntityConvertor())
                .build());
        setAccountService(new AccountServiceBuilder()
                .setAccountDao(new DaoFactory().getAccountDAO())
                .setExpenseDAO(new DaoFactory().getRecordDAO())
                .setUserDAO(new DaoFactory().getUserDAO())
                .setRecordService(getRecordService())
                .setEntityConvertor(new AccountEntityToAccountConvertor())
                .setBeanConvertor(new AccountToAccountEntityConvertor())
                .build());
        setUserService(new UserServiceBuilder()
                .setUserDAO(new DaoFactory().getUserDAO())
                .setAccountService(getAccountService())
                .setEntityConvertor(new UserEntityToUserConvertor())
                .setBeanConvertor(new UserToUserEntityConvertor())
                .build());
    }
    
    private static class RecordServiceBuilder {
        
        private AccountDAO accountDAO;
        private RecordDAO expenseDAO;
        private Convertor<RecordEntity, Record> entityConvertor;
        private Convertor<Record, RecordEntity> beanConvertor;
        
        RecordServiceBuilder setAccountDAO(AccountDAO accountDAO) {
            this.accountDAO = accountDAO;
            return this;
        }

        RecordServiceBuilder setExpenseDAO(RecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }

        RecordServiceBuilder setEntityConvertor(
                Convertor<RecordEntity, Record> entityConvertor) {
            this.entityConvertor = entityConvertor;
            return this;
        }

        RecordServiceBuilder setBeanConvertor(
                Convertor<Record, RecordEntity> beanConvertor) {
            this.beanConvertor = beanConvertor;
            return this;
        }

        RecordService build() {
            return new RecordServiceImpl(accountDAO, expenseDAO, entityConvertor,
                    beanConvertor);
        }
    }
    
    private static class AccountServiceBuilder {
        
        private AccountDAO accountDao;
        private UserDAO userDAO;
        private RecordDAO expenseDAO;
        private RecordService recordService;
        private Convertor<AccountEntity, Account> entityConvertor;
        private Convertor<Account, AccountEntity> beanConvertor;
        
        AccountServiceBuilder setAccountDao(AccountDAO accountDao) {
            this.accountDao = accountDao;
            return this;
        }

        AccountServiceBuilder setUserDAO(UserDAO userDAO) {
            this.userDAO = userDAO;
            return this;
        }

        AccountServiceBuilder setExpenseDAO(RecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }

        AccountServiceBuilder setRecordService(RecordService recordService) {
            this.recordService = recordService;
            return this;
        }

        AccountServiceBuilder setEntityConvertor(
                Convertor<AccountEntity, Account> entityConvertor) {
            this.entityConvertor = entityConvertor;
            return this;
        }

        AccountServiceBuilder setBeanConvertor(
                Convertor<Account, AccountEntity> beanConvertor) {
            this.beanConvertor = beanConvertor;
            return this;
        }

        AccountService build() {
            return new AccountServiceImpl(accountDao, userDAO, expenseDAO, 
                    recordService, entityConvertor, beanConvertor);
        }
    }
    
    private static class UserServiceBuilder {
        
        private UserDAO userDAO ;
        private AccountService accountService;
        private Convertor<UserEntity, User> entityConvertor;
        private Convertor<User, UserEntity> beanConvertor;
        
        UserServiceBuilder setUserDAO(UserDAO userDAO) {
            this.userDAO = userDAO;
            return this;
        }

        UserServiceBuilder setAccountService(AccountService accountService) {
            this.accountService = accountService;
            return this;
        }

        UserServiceBuilder setEntityConvertor(
                Convertor<UserEntity, User> entityConvertor) {
            this.entityConvertor = entityConvertor;
            return this;
        }

        UserServiceBuilder setBeanConvertor(
                Convertor<User, UserEntity> beanConvertor) {
            this.beanConvertor = beanConvertor;
            return this;
        }

        UserService build() {
            return new UserServiceImpl(userDAO, accountService, entityConvertor, 
                    beanConvertor);
        }
    }
    
    private void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    private void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    
    private void setRecordService(RecordService 
            expenseRecordService) {
        this.recordService = expenseRecordService;
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public AccountService getAccountService() {
        return accountService;
    }
    
    public RecordService getRecordService() {
        return recordService;
    }
    
}
