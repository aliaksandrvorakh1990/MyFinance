package by.vorakh.training.my_finance.convertor.impl.request;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.RequestValidator;
import by.vorakh.training.my_finance.validation.UserValidator;

public class RequestToUserConvertor implements Convertor<String, User>, UserValidator,
        RequestValidator{

    private static final String REGEX = "&";
    private static final char ARG_DELIMETER = '=';
    private static final String LOGIN_ARG = "LOGIN";
    private static final String PASS_ARG = "PASSWORD";
    private static final String ROLE_ARG = "ROLE";

    @Override
    public User converte(String object) throws ConvertorException {
    String problem = "[RequestUserConvertor]" + ConvertorException.PROBLEM;
        if ((object == null)) {
            String message = problem + "Request has null value.";
            throw new ConvertorException(message);
        }
        if (!isMultiArgsRequest(object)) {
            String message = problem + "Request has less than two args.";
            throw new ConvertorException(message);
        }
        String login = null;
        String password = null;
        UserRole role = null;
        String[] args = object.split(REGEX);
        for (String arg : args) {
           int beginIndex = arg.indexOf(ARG_DELIMETER);
            String argName = arg.substring(0, beginIndex);
            String argValue = arg.substring(beginIndex + 1);
            switch (argName.toUpperCase()) {
                case LOGIN_ARG:
                    if(!isCorrectLogin(argValue)) {
                        String message = problem + "Wrong login format.";
                        throw new ConvertorException(message);
                    }
                    login = argValue;
                    break;
                case PASS_ARG:
                    if(!isCorrectPassword(argValue)) {
                        String message = problem + "Wrong password format.";
                        throw new ConvertorException(message);
                    }
                    password = argValue;
                    break;
                case ROLE_ARG:
                    if(!isCorrectRole(argValue)) {
                        String message = problem + "Nonexistent role.";
                        throw new ConvertorException(message);
                    }
                    role = UserRole.valueOf(argValue);
                    break;
            }
        }
        return new User(login, password, role);
    }

}
