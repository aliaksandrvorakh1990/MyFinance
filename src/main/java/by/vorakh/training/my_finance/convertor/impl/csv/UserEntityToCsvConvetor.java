package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.validation.dao_entity.UserEntityValidator;

public class UserEntityToCsvConvetor implements Convertor<UserEntity, String>, 
        UserEntityValidator {

    @Override
    public String converte(UserEntity object) throws ConvertorException {
        if (!isCorrectEntity(object)) {
            String message = "[UserEntityToCsvConvetor]" + PROBLEM + "UserEntity"
                    + " has null value or One and more fields have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getLogin(), 
                object.getPassword(), object.getRole());
    }

}
