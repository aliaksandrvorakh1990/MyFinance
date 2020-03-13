package by.vorakh.training.my_finance.validation.dao_entity;

import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class UserEntityValidator {
    
    private UserEntityValidator() {}
    
    public static boolean isCorrectEntity(UserEntity entity) {
        return ((entity != null) && ((entity.getLogin() != null) 
                && (entity.getPassword() != null) 
                && (entity.getRole() != null)));
    }

}
