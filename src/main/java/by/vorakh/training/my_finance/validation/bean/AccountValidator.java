package by.vorakh.training.my_finance.validation.bean;

import by.vorakh.training.my_finance.bean.Account;

public interface AccountValidator {
    
    default boolean isCorrectForConverting(Account account) {
        return ((account != null) && ((account.getId() != null) 
                && (account.getName() != null) 
                && (account.getBalance() != null)));
    }

}
