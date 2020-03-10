package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface AccountDAO extends Crud<AccountEntity, String> {
    
    List<AccountEntity> getAll(String userId) throws DAOException;

}
