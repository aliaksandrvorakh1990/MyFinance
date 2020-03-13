package by.vorakh.training.my_finance.convertor.impl.request;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isMultiArgsRequest;
import static by.vorakh.training.my_finance.validation.type.CurrencyValidator.isCorrectCurrency;
import static by.vorakh.training.my_finance.validation.type.NameValidator.isCorrectExpenseType;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class RequestToExpenseRecordConvertor implements 
        Convertor<String, Record> {
    
    private static final String REGEX = "&";
    private static final char ARG_DELIMETER = '=';
    private static final String AMOUNT = "AMOUNT";
    private static final String TYPE = "TYPE";
    
    @Override
    public Record converte(String object) throws ConvertorException {
        if(!isMultiArgsRequest(object)) {
            String message = "Request has less than two args or "
                    + "null value.";
            throw new ConvertorException(message);
        }
        ExpenseType type = null;
        BigDecimal amount = null;
        String[] args = object.split(REGEX);
        for (String arg : args) {
            int beginIndex = arg.indexOf(ARG_DELIMETER);
            String argName = arg.substring(0, beginIndex);
            String argValue = arg.substring(beginIndex + 1);
            switch (argName.toUpperCase()) {
                case TYPE:
                    if(!isCorrectExpenseType(argValue)) {
                        String message = "Nonexistent type.";
                        throw new ConvertorException(message);
                    }
                    type = ExpenseType.valueOf(argValue);
                    break;
                case AMOUNT:
                    if (!isCorrectCurrency(argValue)) {
                        String message = "Amount has not Currency "
                                + "format.";
                        throw new ConvertorException(message);
                    }
                    amount = new BigDecimal(argValue)
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                    break;
            }
        }
        return new Record(amount, type);
    }

}
