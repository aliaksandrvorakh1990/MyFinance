package by.vorakh.training.my_finance.dao;

import java.util.List;

import by.vorakh.training.my_finance.dao.exception.DAOException;

public interface Crud<T,K> {

    List<T> getAll() throws DAOException;

    T getById(K id) throws DAOException;

    K create(T object) throws DAOException;

    boolean update(T object) throws DAOException;

    boolean delete(K id) throws DAOException;

}
