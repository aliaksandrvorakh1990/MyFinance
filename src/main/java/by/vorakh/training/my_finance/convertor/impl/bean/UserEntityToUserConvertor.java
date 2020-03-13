package by.vorakh.training.my_finance.convertor.impl.bean;

import static by.vorakh.training.my_finance.validation.dao_entity.UserEntityValidator.isCorrectEntity;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class UserEntityToUserConvertor implements Convertor<UserEntity, User> {

    @Override
    public User converte(UserEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "UserEntity has null value or one and more "
                    + "fields have null value.";
            throw new ConvertorException(message);
        }
        return new User(object.getLogin(), object.getPassword(), 
                object.getRole());
    }

}
