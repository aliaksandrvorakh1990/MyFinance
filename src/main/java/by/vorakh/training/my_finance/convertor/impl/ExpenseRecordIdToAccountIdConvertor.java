package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.IdValidator;

public class ExpenseRecordIdToAccountIdConvertor implements 
	    Convertor<String, String>, IdValidator {

    @Override
    public String converte(String object) throws ConvertorException {
	    if (!isExpenseRecordId(object)) {
	        String message = ConvertorException.PROBLEM 
	                + "This is not Expense Record Id";
            throw new ConvertorException(message);
	    }
	    char delimeter = 'T';
        int beginIndex = object.indexOf(delimeter);
        String accountId = object.substring(0, beginIndex);
        accountId = (isAccountId(accountId)) ? accountId : null;
	    return accountId;
    }

}
