package by.vorakh.training.my_finance.convertor.impl.bean;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.validation.dao_entity.UserEntityValidator;

public class UserEntityToUserConvertor implements Convertor<UserEntity, User>, 
        UserEntityValidator {

    @Override
    public User converte(UserEntity object) {
        if (!isCorrectEntity(object)) {
            String message = new StringBuilder(ConvertorException.PROBLEM)
                    .append("UserEntity has null value or one and more "
                            + "fields have null value.")
                    .toString();
            throw new ConvertorException(message);
        }
        return new User(object.getLogin(), object.getPassword(), 
                object.getRole());
    }

}
