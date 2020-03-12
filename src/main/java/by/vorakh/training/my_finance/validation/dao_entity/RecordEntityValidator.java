package by.vorakh.training.my_finance.validation.dao_entity;

import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public interface RecordEntityValidator {
    
    default boolean isCorrectEntity(RecordEntity entity) {
        return ((entity != null) && ((entity.getId() != null) 
                && (entity.getAmount() != null) 
                && (entity.getType() != null)));
    }

}
