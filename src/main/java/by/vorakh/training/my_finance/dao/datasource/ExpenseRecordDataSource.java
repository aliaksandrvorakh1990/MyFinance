package by.vorakh.training.my_finance.dao.datasource;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public interface ExpenseRecordDataSource extends FileDataSource<String, Record> {
    
    public Map<String, Record> read(String path) throws
             DataSourceException;

    public void addTo(Map<String, Record> map, Record object);
    
    public String getPathToFile(Record record) throws DataSourceException;
    
    public String getPathToFile(Account account) throws DataSourceException;
    
    public String getPathToFile(String id) throws DataSourceException;
    
    public void write(Record record, String path, boolean append) throws
            DataSourceException ;
    
    public void write(Collection<Record> records, boolean append) throws
            DataSourceException;
    public void delete(String path) throws DataSourceException;
    
}
