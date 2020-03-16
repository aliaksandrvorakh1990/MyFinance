package by.vorakh.training.my_finance.validation.bean;

import by.vorakh.training.my_finance.bean.Account;

public class AccountValidator {
    
    private AccountValidator() {}
    
    public static boolean isCorrectForWriting(Account account) {
        return ((account != null) && ((account.getId() != null) 
                && (account.getName() != null) 
                && (account.getBalance() != null)));
    }

}
