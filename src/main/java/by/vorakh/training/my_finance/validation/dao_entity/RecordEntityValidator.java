package by.vorakh.training.my_finance.validation.dao_entity;

import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityValidator {
    
    private RecordEntityValidator() {}
    
    public static boolean isCorrectEntity(RecordEntity entity) {
        return ((entity != null) && ((entity.getId() != null) 
                && (entity.getAmount() != null) 
                && (entity.getType() != null)));
    }

}
