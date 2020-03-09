package by.vorakh.training.my_finance.service.factory;

import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.factory.DaoFactory;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.impl.AccountServiceImpl;
import by.vorakh.training.my_finance.service.impl.ExpenseRecordServiceImpl;
import by.vorakh.training.my_finance.service.impl.UserServiceImpl;

public class ServiceFactory {

    private UserService userService;
    private AccountService accountService;
    private ExpenseRecordService expenseRecordService;
    
    public ServiceFactory() {
        setExpenseRecordService(new ExpenseRecordServiceBuilder()
                .setAccountDAO(new DaoFactory().getAccountDAO())
                .setExpenseDAO(new DaoFactory().getExpenseRecordDAO())
                .build());
        setAccountService(new AccountServiceBuilder()
                .setAccountDao(new DaoFactory().getAccountDAO())
                .setExpenseDAO(new DaoFactory().getExpenseRecordDAO())
                .setUserDAO(new DaoFactory().getUserDAO())
                .build());
        setUserService(new UserServiceBuilder()
                .setUserDAO(new DaoFactory().getUserDAO())
                .setAccountService(getAccountService())
                .build());
    }
    
    private static class ExpenseRecordServiceBuilder {
        
        private AccountDAO accountDAO;
        private ExpenseRecordDAO expenseDAO;
        
        ExpenseRecordServiceBuilder setAccountDAO(AccountDAO accountDAO) {
            this.accountDAO = accountDAO;
            return this;
        }

        ExpenseRecordServiceBuilder setExpenseDAO(ExpenseRecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }

        ExpenseRecordService build() {
            return new ExpenseRecordServiceImpl(accountDAO, expenseDAO);
        }
    }
    
    private static class AccountServiceBuilder {
        
        private AccountDAO accountDao;
        private UserDAO userDAO;
        private ExpenseRecordDAO expenseDAO;
        
        AccountServiceBuilder setAccountDao(AccountDAO accountDao) {
            this.accountDao = accountDao;
            return this;
        }

        AccountServiceBuilder setUserDAO(UserDAO userDAO) {
            this.userDAO = userDAO;
            return this;
        }

        AccountServiceBuilder setExpenseDAO(ExpenseRecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }

        AccountService build() {
            return new AccountServiceImpl(accountDao, userDAO, expenseDAO);
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
    
    private void setExpenseRecordService(ExpenseRecordService 
            expenseRecordService) {
        this.expenseRecordService = expenseRecordService;
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public AccountService getAccountService() {
        return accountService;
    }
    
    public ExpenseRecordService getExpenseRecordService() {
        return expenseRecordService;
    }
    
}
