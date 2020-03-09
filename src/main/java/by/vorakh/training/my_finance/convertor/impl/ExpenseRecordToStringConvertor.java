package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class ExpenseRecordToStringConvertor implements
        Convertor<ExpenseRecord, String>, NotNullValidator {

    private static final String FORMAT = "id=%s&amount=%s&type=%s";

    @Override
    public String converte(ExpenseRecord object) throws ConvertorException {
        if ((isEqualsNull(object))) {
            String message = "Unable to execute convertion operation: "
                    + "ExpenseRecord has null value.";
            throw new ConvertorException(message);
        }
        String str = String.format(FORMAT, object.getId(), object.getAmount(),
                object.getType());
        return str;
    }

}
