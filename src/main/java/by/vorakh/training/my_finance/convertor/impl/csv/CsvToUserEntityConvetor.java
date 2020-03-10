package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.validation.csv.UserEntityCsvValidator;

public class CsvToUserEntityConvetor implements Convertor<String, UserEntity>, 
        UserEntityCsvValidator {

    @Override
    public UserEntity converte(String object) throws ConvertorException {
        if (!isCorrectUserCsv(object)) {
            String message = "[UserEntityToCsvConvetor]" + PROBLEM + "CVS has"
                    + " a wrong format for user entity";
            throw new ConvertorException(message);
        }
        String[] values = object.split(",");
        String login = values[0];
        String password = values[1];
        UserRole role = UserRole.valueOf(values[2]);
        return new UserEntity(login, password, role);
    }

}
