package by.vorakh.training.my_finance.dao.datasource.builder;

import by.vorakh.training.my_finance.dao.datasource.*;

public class DataSourceBuilder {
    
    private UserDataSource userDataSourse;
    
    private AccountDataSource accountDataSource;
    
    private ExpenseRecordDataSource expenseRecordDataSource;

    public UserDataSource getUserDataSourse() {
        return userDataSourse;
    }

    public AccountDataSource getAccountDataSource() {
        return accountDataSource;
    }

    public ExpenseRecordDataSource getExpenseRecordDataSource() {
        return expenseRecordDataSource;
    }

    public DataSourceBuilder setUserDataSourse(UserDataSource userDataSourse) {
        this.userDataSourse = userDataSourse;
        return this;
    }

    public DataSourceBuilder setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
        return this;
    }

    public DataSourceBuilder setExpenseRecordDataSource(ExpenseRecordDataSource expenseRecordDataSource) {
        this.expenseRecordDataSource = expenseRecordDataSource;
        return this;
    }
    
    

}
