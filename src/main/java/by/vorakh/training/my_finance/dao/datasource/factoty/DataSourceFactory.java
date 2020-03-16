package by.vorakh.training.my_finance.dao.datasource.factoty;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.AccountToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToUserEntityConvetor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.UserEntityToCsvConvetor;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.UserEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.AccountCsvDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.RecordCsvDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.csv.UserEntityCsvDataSourceImpl;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class DataSourceFactory {
    
    private UserEntityCsvDataSource userDataSource;
    private AccountCsvDataSource accountDataSource;
    private RecordCsvDataSource recordDataSource;
    
    public DataSourceFactory() {
        setUserDataSource(new UserDataSourceBuilder()
                .setCsvToUserEntityConvertor(new CsvToUserEntityConvetor())
                .setUserEntityToCsvConvertor(new UserEntityToCsvConvetor())
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
                Convertor<String, Account> csvToAccountEntityConvertor) {
            this.csvToAccountConvertor = csvToAccountEntityConvertor;
            return this;
        }

        AccountDataSourceBuilder setAccountToCsvConvertor(
                Convertor<Account, String> accountEntityToCsvConvertor) {
            this.accountToCsvConvertor = accountEntityToCsvConvertor;
            return this;
        }

        AccountCsvDataSource build() {
            return new AccountCsvDataSourceImpl(
                    csvToAccountConvertor,accountToCsvConvertor);
        }
    }
    
    private static class UserDataSourceBuilder {
        
        private Convertor<String, UserEntity> csvToUserEntityConvertor;
        private Convertor<UserEntity, String> userEntityToCsvConvertor;

        UserDataSourceBuilder setCsvToUserEntityConvertor(
                Convertor<String, UserEntity> csvToUserEntityConvertor) {
            this.csvToUserEntityConvertor = csvToUserEntityConvertor;
            return this;
        }

        UserDataSourceBuilder setUserEntityToCsvConvertor(
                Convertor<UserEntity, String> userEntityToCsvConvertor) {
            this.userEntityToCsvConvertor = userEntityToCsvConvertor;
            return this;
        }

        UserEntityCsvDataSource build() {
            return new UserEntityCsvDataSourceImpl(csvToUserEntityConvertor, 
                    userEntityToCsvConvertor);
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
    
    private void setUserDataSource(UserEntityCsvDataSource userDataSource) {
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

    public UserEntityCsvDataSource getUserDataSourse() {
        return userDataSource;
    }

    public AccountCsvDataSource getAccountDataSource() {
        return accountDataSource;
    }

    public RecordCsvDataSource getRecordDataSource() {
        return recordDataSource;
    }

}
