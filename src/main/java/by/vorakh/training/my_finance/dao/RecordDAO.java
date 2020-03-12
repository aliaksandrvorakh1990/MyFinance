package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface RecordDAO extends Crud<RecordEntity, String> {

    List<RecordEntity> getAll(String accountId) throws DAOException ;

    void deleteById(String accountId) throws DAOException ;

}
