package by.vorakh.training.my_finance.dao.datasource.impl;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.ExpenseRecordDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class ExpenseDataSourceImpl implements ExpenseRecordDataSource {

    private static final String PATH_FORMAT =
            "./src/main/resources/records/%s.txt";

    private Convertor<String, Record> stringConvertor;

    private Convertor<Record, String> recordConvertor;

    public ExpenseDataSourceImpl(Convertor<String, Record> stringConvertor,
            Convertor<Record, String> recordConvertor) {
        this.stringConvertor = stringConvertor;
        this.recordConvertor = recordConvertor ;
    }

    @Override
    public void addTo(Map<String, Record> map, Record object) {
        map.put(object.getId(), object);
    }
    
    @Override
    public String getPathToFile(Record record) throws DataSourceException {
        if (isEqualsNull(record)) {
            String message = "Unable to execute path getting operation"
                    + "Record has null value.";
            throw new DataSourceException(message);
        }
        String id = record.getId();
        return getPathToFile(id);
    }
    
    @Override
    public String getPathToFile(Account account) throws DataSourceException {
        if(isEqualsNull(account)) {
            String message = "Unable to execute path getting operation"
                    + "Record has null value.";
            throw new DataSourceException(message);
        }
        String path = String.format(PATH_FORMAT, account.getId());
        return path;
    }

    @Override
    public String getPathToFile(String id) throws DataSourceException {
        if(isEqualsNull(id)) {
            String message = "Unable to execute path getting operation"
                    + "id has null value.";
            throw new DataSourceException(message);
        }
        char delimeter = 'T';
        int beginIndex = id.indexOf(delimeter);
        String fileName = id.substring(0, beginIndex);
        String path = String.format(PATH_FORMAT, fileName);
        return path;
    }
    
    @Override
    public Map<String, Record> read(String path) throws
            DataSourceException{
        return this.read(path, stringConvertor);
    }
    
    @Override
    public void write(Record record, String path, boolean append) throws
            DataSourceException {
        this.write(record, append, path, recordConvertor);
    }
    
    @Override
    public void write(Collection<Record> records, boolean append) throws
            DataSourceException {
        final String PROBLEM = "Unable to execute file writing operation:";
        if (isEqualsNull(records)) {
            String message = PROBLEM + "Records Collection has null value.";
            throw new DataSourceException(message);
        }
        for (Record record : records) {
            try {
                String path = getPathToFile(record);
                write(record, path, append);
            } catch (DataSourceException e) {
                String message = PROBLEM + e.getMessage();
                throw new DataSourceException(message, e);
            }
        }
    }
    
    @Override
    public void delete(String path) throws DataSourceException {
        final String PROBLEM = "Unable to execute file deleting operation:";
        if (isEqualsNull(path)) {
            String message = PROBLEM + "Path has null value.";
            throw new DataSourceException(message);
        }
        File deletedFile = new File(path);
        if (!isExistedFile(deletedFile)) {
            String message = PROBLEM + "File is not exist or is not a file.";
            throw new DataSourceException(message);
        }
        deletedFile.delete();
    }

}
