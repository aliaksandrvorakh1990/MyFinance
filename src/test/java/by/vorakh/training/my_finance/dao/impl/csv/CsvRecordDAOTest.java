package by.vorakh.training.my_finance.dao.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvRecordDAOTest {
    
    @Mock
    private RecordCsvDataSource recordDataSource;
    @Mock
    private AccountCsvDataSource accountDataSource;
    
    
    @InjectMocks
    private CsvRecordDAO dao;
    
    private Map<String,Record> recordMap1;
    private Map<String,Record> recordMap2;
    private Map<String,Account> accountMap;
    private String path;
    private String path1;
    private String path2;
    
    
    @Before
    public void init() {
        accountMap = new LinkedHashMap<String, Account>();
        Account account1 = new Account("MrRobot@1583824237692", 
                "MyFirstAccount", new BigDecimal(100).setScale(2));
        Account account2 = new Account("MrXXX@1583996205058", 
                "Visa", new BigDecimal("150.99").setScale(2));
        accountMap.put(account1.getId(), account1);
        accountMap.put(account2.getId(), account2);
        recordMap1 = new LinkedHashMap<String,Record>();
        recordMap2 = new LinkedHashMap<String,Record>();
        Record record11 = new Record(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        Record record12 = new Record(
                "MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD);
        recordMap1.put(record11.getId(), record11);
        recordMap1.put(record12.getId(), record12);
        Record record21 = new Record(
                "MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER);
        Record record22 = new Record(
                "MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH);
        recordMap2.put(record21.getId(), record21);
        recordMap2.put(record22.getId(), record22);
        path = "./csv/accounts.csv";
        path1 = "./csv/records/MrRobot@1583824237692.csv";
        path2 = "./csv/records/MrXXX@1583996205058.csv";
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws DataSourceException, DAOException {
        List<Record> expected = new ArrayList<>();
        expected.add(new Record("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL));
        expected.add(new Record("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD));
        expected.add(new Record("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        expected.add(new Record("MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH));
        when(accountDataSource.read(path)).thenReturn(accountMap);
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        when(recordDataSource.read(path2)).thenReturn(recordMap2);
        List<Record>  actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll_Account_ID() throws DataSourceException, 
            DAOException {
        List<Record> expected = new ArrayList<>();
        expected.add(new Record("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL));
        expected.add(new Record("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD));
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        String accountId = "MrRobot@1583824237692";
        List<Record> actual = dao.getAll(accountId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DataSourceException, DAOException {
        Record expected = new Record(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        String id = "MrRobot@1583824237692@1583924900106";
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        Record actual = dao.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() throws DataSourceException, DAOException {
        String expected = "MrRobot@1583824237692@1583924900106";
        Record newRecord = new Record(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        
        String actual = dao.create(newRecord);
        verify(recordDataSource, times(1)).write(newRecord, path1);
        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdate() throws DataSourceException, DAOException {
        Record updatedRecord = new Record(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(250).setScale(2),ExpenseType.COMMUNAL);
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        boolean actual = dao.update(updatedRecord);
        verify(recordDataSource, times(1)).clearFile(path1);
        verify(recordDataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDelete() throws DataSourceException, DAOException {
        String id = "MrRobot@1583824237692@1583924900106";
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        boolean actual = dao.delete(id);
        verify(recordDataSource, times(1)).clearFile(path1);
        verify(recordDataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }

}
