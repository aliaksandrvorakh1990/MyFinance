package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface ExpenseRecordDAO extends Crud<Record, String> {

    List<Record> getAll(Account account) throws DAOException ;

    void delete(Account account) throws DAOException ;

}
