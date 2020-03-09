package by.vorakh.training.my_finance.service;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public interface AccountService extends Service<Account, String> {
    
    List<Account> getAll(String userId) throws ServiceException ;

}

