package by.vorakh.training.my_finance.convertor.impl.entity;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.validation.bean.AccountValidator;

public class AccountToAccountEntityConvertor implements 
        Convertor<Account, AccountEntity>, AccountValidator {

    @Override
    public AccountEntity converte(Account object) {
        if (!isCorrectForConverting(object)) {
            String message = new StringBuilder(ConvertorException.PROBLEM)
                    .append("Record has null value or one and more "
                            + "fields have null value.")
                    .toString();
            throw new ConvertorException(message);
        }
        return new AccountEntity(object.getId(), object.getName(), 
                object.getBalance());
    }

}
