package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface AccountDAO extends Crud<Account, String> {
    
    List<Account> getAll(String userId) throws DAOException;

}
