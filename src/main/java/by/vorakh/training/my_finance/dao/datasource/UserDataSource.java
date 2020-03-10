package by.vorakh.training.my_finance.dao.datasource;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public interface UserDataSource extends FileDataSource<String, User>  {
    
    public void addTo(Map<String, User> map, User object);
    
    public String getPathToFile();
    
    public Map<String, User> read(String path) throws DataSourceException;
    
    public void write(User user, String path, boolean append) throws 
            DataSourceException;
    
    public void write(Collection<User> users, boolean append) throws
            DataSourceException;
}
