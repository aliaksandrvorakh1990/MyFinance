package by.vorakh.training.my_finance.validation.dao_entity;

import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public interface AccountEntityValidator {

    default boolean isCorrectEntity(AccountEntity entity) {
        return ((entity != null) && ((entity.getId() != null) 
                && (entity.getName() != null) 
                && (entity.getBalance() != null)));
    }
    
}
