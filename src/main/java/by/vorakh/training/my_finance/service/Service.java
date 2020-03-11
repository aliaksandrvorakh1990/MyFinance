package by.vorakh.training.my_finance.service;

import java.util.List;

import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public interface Service<T,V> extends NotNullValidator{
    
    List<T> getAll() throws ServiceException;
    
    T getById(V id) throws ServiceException;
    
    V create(T object) throws ServiceException;
    
    Boolean deleteById(V id) throws ServiceException ;

}
