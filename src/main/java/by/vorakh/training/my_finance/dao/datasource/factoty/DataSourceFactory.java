package by.vorakh.training.my_finance.dao.datasource.factoty;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.AccountToStringConvertor;
import by.vorakh.training.my_finance.convertor.impl.ExpenseRecordToStringConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToExpenseRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToUserConvertor;
import by.vorakh.training.my_finance.convertor.impl.UserToStringConvertor;
import by.vorakh.training.my_finance.dao.datasource.AccountDataSource;
import by.vorakh.training.my_finance.dao.datasource.ExpenseRecordDataSource;
import by.vorakh.training.my_finance.dao.datasource.UserDataSource;
import by.vorakh.training.my_finance.dao.datasource.impl.AccountDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.ExpenseDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.UserDataSourceImpl;

public class DataSourceFactory {
    
    private UserDataSource userDataSourse;
    private AccountDataSource accountDataSource;
    private ExpenseRecordDataSource expenseRecordDataSource;
    
    public DataSourceFactory() {
        setUserDataSourse(new UserDataSourceBuilder()
                .setStringConvertor(new StringToUserConvertor())
                .setUserConvertor(new UserToStringConvertor())
                .build());
        setAccountDataSource(new AccountDataSourceBuilder()
                .setAccountConvertor( new AccountToStringConvertor())
                .setStringConvertor(new StringToAccountConvertor())
                .build());
        setExpenseRecordDataSource(new ExpenseRecordDataSourceBuilder()
                .setRecordConvertor(new ExpenseRecordToStringConvertor())
                .setStringConvertor(new StringToExpenseRecordConvertor())
                .build());
    }
    
    private static class AccountDataSourceBuilder {
        
        private Convertor<String, Account> stringConvertor;
        private Convertor<Account, String> accountConvertor;
        
        AccountDataSourceBuilder setStringConvertor(Convertor<String, Account> stringConvertor) {
            this.stringConvertor = stringConvertor;
            return this;
        }
        AccountDataSourceBuilder setAccountConvertor(Convertor<Account, String> accountConvertor) {
            this.accountConvertor = accountConvertor;
            return this;
        }
        AccountDataSourceImpl build() {
            return new AccountDataSourceImpl(stringConvertor,accountConvertor);
        }
    }
    
    private static class UserDataSourceBuilder {
        
        private Convertor<String, User> stringConvertor;
        private Convertor<User, String> userConvertor;
        
        UserDataSourceBuilder setStringConvertor(Convertor<String, User> 
                stringConvertor) {
            this.stringConvertor = stringConvertor;
            return this;
        }

        UserDataSourceBuilder setUserConvertor(Convertor<User, String> 
                userConvertor) {
            this.userConvertor = userConvertor;
            return this;
        }

        UserDataSourceImpl build() {
            return new UserDataSourceImpl(stringConvertor,userConvertor);
        }
    }
    
    private static class ExpenseRecordDataSourceBuilder {
        private Convertor<String, Record> stringConvertor;
        private Convertor<Record, String> recordConvertor;
        
        ExpenseRecordDataSourceBuilder setStringConvertor(
                Convertor<String, Record> stringConvertor) {
            this.stringConvertor = stringConvertor;
            return this;
        }
        
        ExpenseRecordDataSourceBuilder setRecordConvertor(
                Convertor<Record, String> recordConvertor) {
            this.recordConvertor = recordConvertor;
            return this;
        }
        
        ExpenseDataSourceImpl build() {
            return new ExpenseDataSourceImpl(stringConvertor,recordConvertor);
        }
    }
    
    private void setUserDataSourse(UserDataSource userDataSourse) {
        this.userDataSourse = userDataSourse;
    }

    private void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    private void setExpenseRecordDataSource(ExpenseRecordDataSource expenseRecordDataSource) {
        this.expenseRecordDataSource = expenseRecordDataSource;
    }

    public UserDataSource getUserDataSourse() {
        return userDataSourse;
    }

    public AccountDataSource getAccountDataSource() {
        return accountDataSource;
    }

    public ExpenseRecordDataSource getExpenseRecordDataSource() {
        return expenseRecordDataSource;
    }

}
