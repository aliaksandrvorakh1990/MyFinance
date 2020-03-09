package by.vorakh.training.my_finance.convertor.impl;

import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class StringToExpenseRecordConvertor implements
        Convertor<String, ExpenseRecord>, NotNullValidator {

    private static final String FIELD_DELIMETER = "&";
    private static final char DELIMETER = '=';
    private static final String ID = "ID";
    private static final String TYPE = "TYPE";
    private static final String AMOUNT = "AMOUNT";

    @Override
    public ExpenseRecord converte(String object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = "Unable to execute convertion operation: "
                    + "String has null value.";
            throw new ConvertorException(message);
        }
        String id = null;
        BigDecimal amount = null;
        ExpenseType type = null;
        String[] fields = object.split(FIELD_DELIMETER);
        for (String field : fields) {
            int beginIndex = field.indexOf(DELIMETER);
            String fieldName = field.substring(0, beginIndex);
            String fieldValue = field.substring(beginIndex + 1);
            switch (fieldName.toUpperCase()) {
                case ID:
                    id = fieldValue;
                    break;
                case AMOUNT:
                    try {
                        amount = new BigDecimal(fieldValue);
                        break;
                    } catch (NumberFormatException e) {
                        String message = "Unable to execute convertion operation: "
                            + "Amount in string has wrong format.";
                        throw new ConvertorException(message);
                    }
                case TYPE:
                    type = ExpenseType.valueOf(fieldValue);
                    break;
            }
        }
        return new ExpenseRecord(id, amount, type);
    }

}
