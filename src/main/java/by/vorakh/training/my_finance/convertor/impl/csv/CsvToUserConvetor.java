package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.csv.UserEntityCsvValidator.isCorrectUserCsv;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToUserConvetor implements Convertor<String, User> {

    @Override
    public User converte(String object) {
        if (!isCorrectUserCsv(object)) {
            String message = "CVS has a wrong format for user entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String login = values[0];
        String password = values[1];
        UserRole role = UserRole.valueOf(values[2]);
        return new User(login, password, role);
    }

}
