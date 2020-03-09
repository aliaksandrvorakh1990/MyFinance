package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface ExpenseRecordDAO extends Crud<ExpenseRecord, String> {

    List<ExpenseRecord> getAll(Account account) throws DAOException ;

    void delete(Account account) throws DAOException ;

}
