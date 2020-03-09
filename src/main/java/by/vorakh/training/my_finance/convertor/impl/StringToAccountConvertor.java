package by.vorakh.training.my_finance.convertor.impl;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class StringToAccountConvertor implements Convertor<String, Account>,
        NotNullValidator {

    private static final String FIELD_DELIMETER = "&";
    private static final char DELIMETER = '=';
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String BALANCE = "BALANCE";

    @Override
    public Account converte(String object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = "Unable to execute convertion operation: "
                    + "String has null value.";
            throw new ConvertorException(message);
        }
        String id = null;
        String name = null;
        BigDecimal balance = null;
        String[] fields = object.split(FIELD_DELIMETER);
        for (String field : fields) {
            int beginIndex = field.indexOf(DELIMETER);
            String fieldName = field.substring(0, beginIndex);
            String fieldValue = field.substring(beginIndex + 1);
            switch (fieldName.toUpperCase()) {
                case ID:
                    id = fieldValue;
                    break;
                case BALANCE:
                    try {
                        balance = new BigDecimal(fieldValue);
                        break;
                    } catch (NumberFormatException e) {
                        String message = "Unable to execute convertion operation: "
                            + "Amount in string has wrong format.";
                        throw new ConvertorException(message);
                    }
                case NAME:
                    name = fieldValue;
                    break;
            }
        }
        return new Account(id, name, balance);
    }

}
