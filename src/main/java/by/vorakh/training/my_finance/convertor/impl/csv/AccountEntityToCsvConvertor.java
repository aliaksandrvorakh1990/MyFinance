package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.dao_entity.AccountEntityValidator.isCorrectEntity;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityToCsvConvertor implements
        Convertor< AccountEntity, String> {

    @Override
    public String converte(AccountEntity object) {
        if (!isCorrectEntity(object)) {
            String message = "AccountEntity has null value or One and more "
                    + " fields have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getId(), 
                object.getName(), object.getBalance());
    }

}
