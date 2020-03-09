package by.vorakh.training.my_finance.dao.datasource;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public interface ExpenseRecordDataSource extends FileDataSource<String, ExpenseRecord> {
    
    public Map<String, ExpenseRecord> read(String path) throws
             DataSourceException;

    public void addTo(Map<String, ExpenseRecord> map, ExpenseRecord object);
    
    public String getPathToFile(ExpenseRecord record) throws DataSourceException;
    
    public String getPathToFile(Account account) throws DataSourceException;
    
    public String getPathToFile(String id) throws DataSourceException;
    
    public void write(ExpenseRecord record, String path, boolean append) throws
            DataSourceException ;
    
    public void write(Collection<ExpenseRecord> records, boolean append) throws
            DataSourceException;
    public void delete(String path) throws DataSourceException;
    
}
