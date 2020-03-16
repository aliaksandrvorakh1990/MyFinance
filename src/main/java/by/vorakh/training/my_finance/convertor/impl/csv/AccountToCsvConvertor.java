package by.vorakh.training.my_finance.convertor.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.AccountValidator.isCorrectForWriting;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class AccountToCsvConvertor implements
        Convertor< Account, String> {

    @Override
    public String converte(Account object) {
        if (!isCorrectForWriting(object)) {
            String message = "AccountEntity has null value or One and more "
                    + " fields have null value.";
            throw new ConvertorException(message);
        }
        return String.format("%s,%s,%s", object.getId(), 
                object.getName(), object.getBalance());
    }

}
