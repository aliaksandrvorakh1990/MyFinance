package by.vorakh.training.my_finance.service;

import java.util.List;

import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public interface ExpenseRecordService extends Service<ExpenseRecord, String> {
    
    List<ExpenseRecord> getAll(String  accountId) throws ServiceException ;

    List<ExpenseRecord> getAll(String accountId, ExpenseType type) throws ServiceException ;
    
}
