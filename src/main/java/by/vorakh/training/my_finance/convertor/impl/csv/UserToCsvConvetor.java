package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.UserValidator.isCorrectForWriting;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class UserToCsvConvetor implements Convertor<User, String> {

    @Override
    public String converte(User object) {
        if (!isCorrectForWriting(object)) {
            String message ="UserEntity has null value or One and more fields "
                    + "have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getLogin(), 
                object.getPassword(), object.getRole());
    }

}
