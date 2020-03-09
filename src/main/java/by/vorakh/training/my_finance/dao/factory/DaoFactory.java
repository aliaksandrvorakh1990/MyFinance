package by.vorakh.training.my_finance.dao.factory;

import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.datasource.AccountDataSource;
import by.vorakh.training.my_finance.dao.datasource.ExpenseRecordDataSource;
import by.vorakh.training.my_finance.dao.datasource.UserDataSource;
import by.vorakh.training.my_finance.dao.datasource.factoty.DataSourceFactory;
import by.vorakh.training.my_finance.dao.impl.FileAccountDAO;
import by.vorakh.training.my_finance.dao.impl.FileExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.impl.FileUserDAO;

public class DaoFactory {
    
    private AccountDAO AccountDAO;
    private ExpenseRecordDAO ExpenseRecordDAO;
    private UserDAO UserDAO;
    
    public DaoFactory () {
        setExpenseRecordDAO(new ExpenseRecordDAOBuilder()
                .setAccountDataSource(new DataSourceFactory()
                        .getAccountDataSource())
                .setExpenseDataSource(new DataSourceFactory()
                        .getExpenseRecordDataSource())
                .build());
        setAccountDAO(new AccountDAOBuilder()
                .setAccountDataSource(new DataSourceFactory()
                        .getAccountDataSource())
                .setExpenseDAO(getExpenseRecordDAO())
                .build());
        setUserDAO(new UserDAOBuilder()
                .setUserDataSource(new DataSourceFactory()
                        .getUserDataSourse())
                .setAccountDAO(getAccountDAO())
                .build());
        
    }

    private static class AccountDAOBuilder {
        
        private AccountDataSource accountDataSource;
        private ExpenseRecordDAO expenseDAO;
        
        AccountDAOBuilder setAccountDataSource(AccountDataSource 
                accountDataSource) {
            this.accountDataSource = accountDataSource;
            return this;
        }
        AccountDAOBuilder setExpenseDAO(ExpenseRecordDAO expenseDAO) {
            this.expenseDAO = expenseDAO;
            return this;
        }
        
        AccountDAO build() {
            return new FileAccountDAO(accountDataSource, expenseDAO);
        }
    }
    
    private static class ExpenseRecordDAOBuilder {
        
        private ExpenseRecordDataSource expenseDataSource;
        private AccountDataSource accountDataSource;
        
        ExpenseRecordDAOBuilder setExpenseDataSource(ExpenseRecordDataSource 
                expenseDataSource) {
            this.expenseDataSource = expenseDataSource;
            return this;
        }
        
        ExpenseRecordDAOBuilder setAccountDataSource(AccountDataSource 
                accountDataSource) {
            this.accountDataSource = accountDataSource;
            return this;
        }

        ExpenseRecordDAO build() {
            return new FileExpenseRecordDAO(expenseDataSource, 
                    accountDataSource);
        }
    }
    
    private static class UserDAOBuilder {
        
        private UserDataSource userDataSource;
        private AccountDAO accountDAO;

        UserDAOBuilder setUserDataSource(UserDataSource userDataSource) {
            this.userDataSource = userDataSource;
            return this;
        }

        UserDAOBuilder setAccountDAO(AccountDAO accountDAO) {
            this.accountDAO = accountDAO;
            return this;
        }

        UserDAO build() {
            return new FileUserDAO(userDataSource, 
                    accountDAO);
        }
    }
    
    private void setAccountDAO(AccountDAO accountDAO) {
        AccountDAO = accountDAO;
    }

    private void setExpenseRecordDAO(ExpenseRecordDAO expenseRecordDAO) {
        ExpenseRecordDAO = expenseRecordDAO;
    }

    private void setUserDAO(UserDAO userDAO) {
        UserDAO = userDAO;
    }

    public AccountDAO getAccountDAO() {
        return AccountDAO;
    }

    public ExpenseRecordDAO getExpenseRecordDAO() {
        return ExpenseRecordDAO;
    }

    public UserDAO getUserDAO() {
        return UserDAO;
    }

}
