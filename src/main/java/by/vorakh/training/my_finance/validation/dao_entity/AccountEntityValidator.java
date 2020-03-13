package by.vorakh.training.my_finance.validation.dao_entity;

import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityValidator {

    private AccountEntityValidator() {}
    
    public static boolean isCorrectEntity(AccountEntity entity) {
        return ((entity != null) && ((entity.getId() != null) 
                && (entity.getName() != null) 
                && (entity.getBalance() != null)));
    }
    
}
