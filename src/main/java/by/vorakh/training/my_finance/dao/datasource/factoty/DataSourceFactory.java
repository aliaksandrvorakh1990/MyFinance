package by.vorakh.training.my_finance.dao.datasource.factoty;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.AccountToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToUserConvetor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.UserToCsvConvetor;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.AccountCsvDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.RecordCsvDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.UserCsvDataSourceImpl;

public class DataSourceFactory {
    
    private UserCsvDataSource userDataSource;
    private AccountCsvDataSource accountDataSource;
    private RecordCsvDataSource recordDataSource;
    
    public DataSourceFactory() {
        setUserDataSource(new UserDataSourceBuilder()
                .setCsvToUserConvertor(new CsvToUserConvetor())
                .setUserToCsvConvertor(new UserToCsvConvetor())
                .build());
        setAccountDataSource(new AccountDataSourceBuilder()
                .setCsvToAccountConvertor(
                        new CsvToAccountConvertor())
                .setAccountToCsvConvertor(
                        new AccountToCsvConvertor())
                .build());
        setRecordDataSource(new RecordDataSourceBuilder()
                .setCsvToRecordConvertor(new CsvToRecordConvertor())
                .setRecordToCsvConvertor(new RecordToCsvConvertor())
                .build());
    }
    
    private static class AccountDataSourceBuilder {
        
        Convertor<String, Account> csvToAccountConvertor;
        Convertor<Account, String> accountToCsvConvertor;
        
        AccountDataSourceBuilder setCsvToAccountConvertor(
                Convertor<String, Account> csvToAccountConvertor) {
            this.csvToAccountConvertor = csvToAccountConvertor;
            return this;
        }

        AccountDataSourceBuilder setAccountToCsvConvertor(
                Convertor<Account, String> accountToCsvConvertor) {
            this.accountToCsvConvertor = accountToCsvConvertor;
            return this;
        }

        AccountCsvDataSource build() {
            return new AccountCsvDataSourceImpl(
                    csvToAccountConvertor,accountToCsvConvertor);
        }
    }
    
    private static class UserDataSourceBuilder {
        
        private Convertor<String, User> csvToUserConvertor;
        private Convertor<User, String> userToCsvConvertor;

        UserDataSourceBuilder setCsvToUserConvertor(
                Convertor<String, User> csvToUserConvertor) {
            this.csvToUserConvertor = csvToUserConvertor;
            return this;
        }

        UserDataSourceBuilder setUserToCsvConvertor(
                Convertor<User, String> userToCsvConvertor) {
            this.userToCsvConvertor = userToCsvConvertor;
            return this;
        }

        UserCsvDataSource build() {
            return new UserCsvDataSourceImpl(csvToUserConvertor, 
                    userToCsvConvertor);
        }
    }
    
    private static class RecordDataSourceBuilder {
        private Convertor<String, Record> csvToRecordConvertor;
        private Convertor<Record, String> recordToCsvConvertor;

        RecordDataSourceBuilder setCsvToRecordConvertor(
                Convertor<String, Record> csvToRecordConvertor) {
            this.csvToRecordConvertor = csvToRecordConvertor;
            return this;
        }

        RecordDataSourceBuilder setRecordToCsvConvertor(
                Convertor<Record, String> recordToCsvConvertor) {
            this.recordToCsvConvertor = recordToCsvConvertor;
            return this;
        }
        
        RecordCsvDataSource build() {
            return new RecordCsvDataSourceImpl(csvToRecordConvertor,
                    recordToCsvConvertor);
        }
    }
    
    private void setUserDataSource(UserCsvDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    private void setAccountDataSource(AccountCsvDataSource 
            accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    private void setRecordDataSource(RecordCsvDataSource 
            recordDataSource) {
        this.recordDataSource = recordDataSource;
    }

    public UserCsvDataSource getUserDataSourse() {
        return userDataSource;
    }

    public AccountCsvDataSource getAccountDataSource() {
        return accountDataSource;
    }

    public RecordCsvDataSource getRecordDataSource() {
        return recordDataSource;
    }

}
