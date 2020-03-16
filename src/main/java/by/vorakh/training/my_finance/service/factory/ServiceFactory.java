package by.vorakh.training.my_finance.service.factory;

import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
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
                .build());
        setAccountService(new AccountServiceBuilder()
                .setAccountDao(new DaoFactory().getAccountDAO())
                .setExpenseDAO(new DaoFactory().getRecordDAO())
                .setUserDAO(new DaoFactory().getUserDAO())
                .setRecordService(getRecordService())
                .build());
        setUserService(new UserServiceBuilder()
                .setUserDAO(new DaoFactory().getUserDAO())
                .setAccountService(getAccountService())
                .build());
    }
    
    private static class RecordServiceBuilder {
        
        private AccountDAO accountDAO;
        private RecordDAO expenseDAO;
       
        RecordServiceBuilder setAccountDAO(AccountDAO accountDAO) {
            this.accountDAO = accountDAO;
            return this;
        }

        RecordServiceBuilder setExpenseDAO(RecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }

        RecordService build() {
            return new RecordServiceImpl(accountDAO, expenseDAO);
        }
    }
    
    private static class AccountServiceBuilder {
        
        private AccountDAO accountDao;
        private UserDAO userDAO;
        private RecordDAO expenseDAO;
        private RecordService recordService;
       
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


        AccountService build() {
            return new AccountServiceImpl(accountDao, userDAO, expenseDAO, 
                    recordService);
        }
    }
    
    private static class UserServiceBuilder {
        
        private UserDAO userDAO ;
        private AccountService accountService;
        
        UserServiceBuilder setUserDAO(UserDAO userDAO) {
            this.userDAO = userDAO;
            return this;
        }

        UserServiceBuilder setAccountService(AccountService accountService) {
            this.accountService = accountService;
            return this;
        }

        UserService build() {
            return new UserServiceImpl(userDAO, accountService);
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
