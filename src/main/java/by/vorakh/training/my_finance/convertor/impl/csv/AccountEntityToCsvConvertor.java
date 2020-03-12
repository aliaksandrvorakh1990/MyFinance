package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.validation.dao_entity.AccountEntityValidator;

public class AccountEntityToCsvConvertor implements AccountEntityValidator, 
        Convertor< AccountEntity, String> {

    @Override
    public String converte(AccountEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "[AccountEntityToCsvConvertor]" + PROBLEM  
                    + "AccountEntity has null value or One and more fields "
                    + "have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getId(), 
                object.getName(), object.getBalance());
    }

}
