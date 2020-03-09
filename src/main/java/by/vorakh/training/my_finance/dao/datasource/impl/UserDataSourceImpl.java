package by.vorakh.training.my_finance.dao.datasource.impl;

import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.UserDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class UserDataSourceImpl implements UserDataSource, NotNullValidator {

    private static final String PATH = "./src/main/resources/users.txt";

    private Convertor<String, User> stringConvertor;

    private Convertor<User, String> userConvertor;

    public UserDataSourceImpl(Convertor<String, User> stringConvertor,
            Convertor<User, String> userConvertor) {
        this.stringConvertor = stringConvertor;
        this.userConvertor = userConvertor;
    }

    @Override
    public void addTo(Map<Integer, User> map, User object) {
        map.put(object.getId(), object);
    }

    @Override
    public String getPathToFile() {
        return PATH;
    }
    
    @Override
    public Map<Integer, User> read(String path) throws
            DataSourceException{
        return this.read(path, stringConvertor);
    }

    @Override
    public void write(User user, String path, boolean append) throws
            DataSourceException {
        this.write(user, append, path, userConvertor);
    }

    @Override
    public void write(Collection<User> users, boolean append) throws
            DataSourceException {
        final String PROBLEM = "Unable to execute file writing operation:";
        if (isEqualsNull(users)) {
            String message = PROBLEM + "Users Collection has null value.";
            throw new DataSourceException(message);
        }
        for (User user : users) {
            try {
                String path = getPathToFile();
                write(user, path, append);
            } catch (DataSourceException e) {
                String message = PROBLEM + e.getMessage();
                throw new DataSourceException(message, e);
            }
        }
    }

}
