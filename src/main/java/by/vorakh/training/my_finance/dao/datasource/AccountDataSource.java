package by.vorakh.training.my_finance.dao.datasource;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public interface AccountDataSource extends FileDataSource<String, Account>{
    
    public Map<String, Account> read(String path) throws DataSourceException;
    
    public void write(Account account, String path, boolean append) throws 
             DataSourceException;
    
    public void write(Collection<Account> accounts, boolean append) throws 
             DataSourceException;
    
    public void addTo(Map<String, Account> map, Account object);
    
    public String getPathToFile() ;

}
