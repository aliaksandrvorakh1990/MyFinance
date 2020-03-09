package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class UserToStringConvertor implements Convertor<User, String>,
        NotNullValidator {

    private static final String FORMAT = "id=%s&login=%s&password=%s&role=%s";

    @Override
    public String converte(User object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = "Unable to execute convertion operation: "
                    + "User has null value.";
            throw new ConvertorException(message);
        }
        String str = String.format(FORMAT,object.getId(), object.getLogin(),
                object.getPassword(), object.getRole());
        return str;
    }

}
