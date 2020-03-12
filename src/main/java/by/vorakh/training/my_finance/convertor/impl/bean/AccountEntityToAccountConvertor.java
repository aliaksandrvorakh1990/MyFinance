package by.vorakh.training.my_finance.convertor.impl.bean;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.validation.dao_entity.AccountEntityValidator;

public class AccountEntityToAccountConvertor implements 
        Convertor<AccountEntity, Account>, AccountEntityValidator {

    @Override
    public Account converte(AccountEntity object) {
        if (!isCorrectEntity(object)) {
            String message = new StringBuilder(ConvertorException.PROBLEM)
                    .append("AccountEntity has null value or one and more "
                            + "fields have null value.")
                    .toString();
            throw new ConvertorException(message);
        }
        return new Account(object.getId(), object.getName(), 
                object.getBalance());
    }

}
