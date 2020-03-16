package by.vorakh.training.my_finance.dao.impl.csv;

import static by.vorakh.training.my_finance.validation.bean.RecordValidator.isCorrectForWriting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvRecordDAO implements RecordDAO {
    
    private final static String PATH_FORMAT= "./csv/records/%s.csv";
    
    private RecordCsvDataSource recordDataSource;
    private AccountCsvDataSource accountDataSource;
    
    public CsvRecordDAO(RecordCsvDataSource dataSource, 
            AccountCsvDataSource accountDataSource) {
        this.recordDataSource = dataSource;
        this.accountDataSource = accountDataSource;
    }

    @Override
    public List<Record> getAll() throws DAOException {
        try {
            List<Record> allRecords = new ArrayList<Record>();
            String accountsPath = "./csv/accounts.csv";
            for (AccountEntity account : accountDataSource
                    .read(accountsPath).values()) {
                String path = getPath(account.getId());
                allRecords.addAll(recordDataSource.read(path).values());
            }
            return allRecords;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }
    
    @Override
    public List<Record> getAll(String accountId) throws DAOException {
        if (accountId == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        try {
            String path = getPath(accountId);
            return new ArrayList<Record>(recordDataSource.read(path).values());
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public Record getById(String id) throws DAOException {
        if (id == null) {
            String message = "User id has null value.";
            throw new DAOException(message);
        }
        try {
            String accountId = getAccountIdFrom(id);
            String path = getPath(accountId);
            Record record = recordDataSource.read(path).get(id);
            return record;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public String create(Record object) throws DAOException {
        if (!isCorrectForWriting(object)) {
            String message = "AccountEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            String accountId = getAccountIdFrom(object.getId());
            String path = getPath(accountId);
            recordDataSource.write(object, path);
            return object.getId();
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean update(Record object) throws DAOException {
        if (!isCorrectForWriting(object)) {
            String message = "AccountEntity has null value or one and more "
                    + "fields have null value.";
            throw new DAOException(message);
        }
        try {
            String accountId = getAccountIdFrom(object.getId());
            String path = getPath(accountId);
            Map<String, Record> records = recordDataSource.read(path);
            String id = object.getId();
            boolean isUpdated = (records.replace(id, object) != null);
            if (isUpdated) {
                recordDataSource.clearFile(path);
                recordDataSource.write(records.values(), path);
            }
            return isUpdated;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public boolean delete(String id) throws DAOException {
        if (id == null) {
            String message = "Record id has null value.";
            throw new DAOException(message);
        }
        try {
            String accountId = getAccountIdFrom(id);
            String path = getPath(accountId);
            Map<String, Record> records = recordDataSource.read(path);
            boolean isDeleted = (records.remove(id) != null);
            if (isDeleted) {
                recordDataSource.clearFile(path);
                recordDataSource.write(records.values(), path);
            }
            return isDeleted;
        } catch (DataSourceException e) {
            String message = e.getMessage();
            throw new DAOException(message, e);
        }
    }

    @Override
    public void deleteById(String accountId) throws DAOException {
        if (accountId == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        String path = getPath(accountId);
        File deletedRecords = new File(path);
        if (deletedRecords.exists()) {
            deletedRecords.delete();
        }
    }
    
    private String getPath(String accountId) throws DAOException {
        if (accountId == null) {
            String message = "Account id has null value.";
            throw new DAOException(message);
        }
        return String.format(PATH_FORMAT, accountId);
    }
    
    private String getAccountIdFrom(String recordId) {
        int lastDelimeter = recordId.lastIndexOf("@");
        return recordId.substring(0, lastDelimeter);
    }

}
