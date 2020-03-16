package by.vorakh.training.my_finance.dao.factory;

import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.factoty.DataSourceFactory;
import by.vorakh.training.my_finance.dao.impl.csv.CsvAccountDAO;
import by.vorakh.training.my_finance.dao.impl.csv.CsvRecordDAO;
import by.vorakh.training.my_finance.dao.impl.csv.CsvUserDAO;

public class DaoFactory {
    
    private AccountDAO accountDAO;
    private RecordDAO recordDAO;
    private UserDAO userDAO;
    
    public DaoFactory () {
        setRecordDAO(new RecordDAOBuilder()
                .setRecordDataSource(new DataSourceFactory()
                        .getRecordDataSource())
                .setAccountDataSource(new DataSourceFactory()
                        .getAccountDataSource())
                .build());
        setAccountDAO(new AccountDAOBuilder()
                .setDataSource(new DataSourceFactory()
                        .getAccountDataSource())
                .build());
        setUserDAO(new UserDAOBuilder()
                .setDataSource(new DataSourceFactory()
                        .getUserDataSourse())
                .build());
    }

    private static class AccountDAOBuilder {
        
        private AccountCsvDataSource dataSource;
        
        AccountDAOBuilder setDataSource(AccountCsvDataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }
        
        AccountDAO build() {
            return new CsvAccountDAO(dataSource);
        }
    }
    
    private static class RecordDAOBuilder {

        private RecordCsvDataSource recordDataSource;
        private AccountCsvDataSource accountDataSource;
        
        RecordDAOBuilder setRecordDataSource(RecordCsvDataSource 
                recordDataSource) {
            this.recordDataSource = recordDataSource;
            return this;
        }

        RecordDAOBuilder setAccountDataSource(AccountCsvDataSource 
                accountDataSource) {
            this.accountDataSource = accountDataSource;
            return this;
        }

        RecordDAO build() {
            return new CsvRecordDAO(recordDataSource,accountDataSource);
        }
    }
    
    private static class UserDAOBuilder {
        
        private UserCsvDataSource dataSource;

        UserDAOBuilder setDataSource(UserCsvDataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        UserDAO build() {
            return new CsvUserDAO(dataSource);
        }
    }
    
    private void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    private void setRecordDAO(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    private void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public RecordDAO getRecordDAO() {
        return recordDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

}
