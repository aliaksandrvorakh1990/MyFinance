package by.vorakh.training.my_finance.convertor.impl.request;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.request.RequestValidator;
import by.vorakh.training.my_finance.validation.type.CurrencyValidator;
import by.vorakh.training.my_finance.validation.type.NameValidator;

public class RequestToAccountConvertor implements Convertor<String, Account>,
        RequestValidator, CurrencyValidator, NameValidator {
    
    private static final String REGEX = "&";
    private static final char ARG_DELIMETER = '=';
    private static final String NAME = "NAME";
    private static final String BALANCE = "BALANCE";

    @Override
    public Account converte(String object) throws ConvertorException {
        String problem = "[RequestToAccountConvertor]"
                + ConvertorException.PROBLEM;
        if (object == null) {
            String message = problem + "Request has null value.";
            throw new ConvertorException(message);
        }
        if(!isMultiArgsRequest(object)) {
            String message = problem + "Request has less than two args.";
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
                        String message = problem + "Account Name has uncorrect"
                                + " format.";
                        throw new ConvertorException(message);
                    }
                    name = argValue;
                    break;
                case BALANCE:
                    if (!isCorrectCurrency(argValue)) {
                        String message = problem + "Balance has not Currency "
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
