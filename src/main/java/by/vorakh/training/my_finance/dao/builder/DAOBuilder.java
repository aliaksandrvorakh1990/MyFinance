package by.vorakh.training.my_finance.dao.builder;

import by.vorakh.training.my_finance.dao.*;

public class DAOBuilder {
    
    private AccountDAO AccountDAO;
    
    private ExpenseRecordDAO ExpenseRecordDAO;
    
    private UserDAO UserDAO;

    public AccountDAO getAccountDAO() {
        return AccountDAO;
    }

    public ExpenseRecordDAO getExpenseRecordDAO() {
        return ExpenseRecordDAO;
    }

    public UserDAO getUserDAO() {
        return UserDAO;
    }

    public DAOBuilder setAccountDAO(AccountDAO accountDAO) {
        AccountDAO = accountDAO;
        return this;
    }

    public DAOBuilder setExpenseRecordDAO(ExpenseRecordDAO expenseRecordDAO) {
        ExpenseRecordDAO = expenseRecordDAO;
        return this;
    }

    public DAOBuilder setUserDAO(UserDAO userDAO) {
        UserDAO = userDAO;
        return this;
    }

}
