package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class StringToUserConvertor implements Convertor<String, User> ,
        NotNullValidator {

    private static final String FIELD_DELIMETER = "&";
    private static final char DELIMETER = '=';
    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String ROLE = "ROLE";

    @Override
    public User converte(String object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = "Unable to execute convertion operation: "
                    + "String has null value.";
            throw new ConvertorException(message);
        }
        Integer id = null;
        String login = null;
        String password = null;
        UserRole role = null;
        String[] fields = object.split(FIELD_DELIMETER);
        for (String field : fields) {
            int beginIndex = field.indexOf(DELIMETER);
            String fieldName = field.substring(0, beginIndex);
            String fieldValue = field.substring(beginIndex + 1);
            switch (fieldName.toUpperCase()) {
                case ID:
                    id = Integer.valueOf(fieldValue);
                    break;
                case LOGIN:
                    login = fieldValue;
                    break;
                case PASSWORD:
                    password = fieldValue;
                    break;
                case ROLE:
                    role = UserRole.valueOf(fieldValue);
                    break;
            }
        }
        return new User(id, login, password, role);
    }

}
