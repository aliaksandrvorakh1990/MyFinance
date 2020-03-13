package by.vorakh.training.my_finance.convertor.impl.entity;

import static by.vorakh.training.my_finance.validation.bean.UserValidator.isCorrectForConverting;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;


public class UserToUserEntityConvertor implements Convertor<User, UserEntity> {
    
    @Override
    public UserEntity converte(User object) {
        if (!isCorrectForConverting(object)) {
            String message = "Record has null value or one and more "
                    + "fields have null value.";
            throw new ConvertorException(message);
        }
        return new UserEntity(object.getLogin(), object.getPassword(), 
                object.getRole());
    }

}
