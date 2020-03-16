package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface RecordDAO extends Crud<Record, String> {

    List<Record> getAll(String accountId) throws DAOException ;

    void deleteById(String accountId) throws DAOException ;

}
