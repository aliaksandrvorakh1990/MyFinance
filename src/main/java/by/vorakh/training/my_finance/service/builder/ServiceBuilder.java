package by.vorakh.training.my_finance.service.builder;

import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.UserService;

public class ServiceBuilder {
    
    private UserService userService;
    
    private AccountService accountService;
    
    private ExpenseRecordService expenseRecordService;

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ExpenseRecordService getExpenseRecordService() {
        return expenseRecordService;
    }

    public ServiceBuilder setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public ServiceBuilder setAccountService(AccountService accountService) {
        this.accountService = accountService;
        return this;
    }

    public ServiceBuilder setExpenseRecordService(ExpenseRecordService 
	    expenseRecordService) {
        this.expenseRecordService = expenseRecordService;
        return this;
    }
    
}
