package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class AccountToStringConvertor implements Convertor<Account, String>,
        NotNullValidator {

    private static final String FORMAT = "id=%s&name=%s&balance=%s";

    @Override
    public String converte(Account object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = ConvertorException.PROBLEM + "Account has null "
                    + "value.";
            throw new ConvertorException(message);
        }
        String str = String.format(FORMAT, object.getId(), object.getName(),
                object.getBalance());
        return str;
    }

}
