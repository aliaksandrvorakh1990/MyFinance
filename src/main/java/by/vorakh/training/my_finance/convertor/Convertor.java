package by.vorakh.training.my_finance.convertor;

import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public interface Convertor<T, V> {

     V converte(T object) throws ConvertorException;
    
}
