package by.vorakh.training.my_finance.service;

import java.util.List;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public interface ExpenseRecordService extends Service<Record, String> {
    
    List<Record> getAll(String  accountId) throws ServiceException ;

    List<Record> getAll(String accountId, ExpenseType type) throws ServiceException ;
    
}
