package by.vorakh.training.my_finance.convertor.impl.request;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isMultiArgsRequest;
import static by.vorakh.training.my_finance.validation.type.CurrencyValidator.isCorrectCurrency;
import static by.vorakh.training.my_finance.validation.type.NameValidator.isCorrectAccountName;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class RequestToAccountConvertor implements Convertor<String, Account> {
    
    private static final String REGEX = "&";
    private static final char ARG_DELIMETER = '=';
    private static final String NAME = "NAME";
    private static final String BALANCE = "BALANCE";

    @Override
    public Account converte(String object) throws ConvertorException {
        if(!isMultiArgsRequest(object)) {
            String message = "Request has less than two args or "
                    + "null value.";
            throw new ConvertorException(message);
        }
        String name = null;
        BigDecimal balance = null;
        String[] args = object.split(REGEX);
        for (String arg : args) {
            int beginIndex = arg.indexOf(ARG_DELIMETER);
            String argName = arg.substring(0, beginIndex);
            String argValue = arg.substring(beginIndex + 1);
            switch (argName.toUpperCase()) {
                case NAME:
                    if (!isCorrectAccountName(argValue)) {
                        String message = "Account Name has uncorrect"
                                + " format.";
                        throw new ConvertorException(message);
                    }
                    name = argValue;
                    break;
                case BALANCE:
                    if (!isCorrectCurrency(argValue)) {
                        String message = "Balance has not Currency "
                                + "format.";
                        throw new ConvertorException(message);
                    }
                    balance = new BigDecimal(argValue)
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                    break;
            }
        }
        return new Account(name, balance);
    }

}
