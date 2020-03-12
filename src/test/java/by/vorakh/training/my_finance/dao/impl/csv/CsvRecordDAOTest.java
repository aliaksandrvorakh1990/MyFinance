package by.vorakh.training.my_finance.dao.impl.csv;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.dao.datasource.csv.AccountEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvRecordDAOTest {
    
    @Mock
    private RecordEntityCsvDataSource recordDataSource;
    @Mock
    private AccountEntityCsvDataSource accountDataSource;
    
    
    @InjectMocks
    private CsvRecordDAO dao;
    
    private Map<String,RecordEntity> recordMap1;
    private Map<String,RecordEntity> recordMap2;
    private Map<String,AccountEntity> accountMap;
    private String path;
    private String path1;
    private String path2;
    
    
    @Before
    public void init() {
        accountMap = new LinkedHashMap<String, AccountEntity>();
        AccountEntity account1 = new AccountEntity("MrRobot@1583824237692", 
                "MyFirstAccount", new BigDecimal(100).setScale(2));
        AccountEntity account2 = new AccountEntity("MrXXX@1583996205058", 
                "Visa", new BigDecimal("150.99").setScale(2));
        accountMap.put(account1.getId(), account1);
        accountMap.put(account2.getId(), account2);
        recordMap1 = new LinkedHashMap<String,RecordEntity>();
        recordMap2 = new LinkedHashMap<String,RecordEntity>();
        RecordEntity record11 = new RecordEntity(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        RecordEntity record12 = new RecordEntity(
                "MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD);
        recordMap1.put(record11.getId(), record11);
        recordMap1.put(record12.getId(), record12);
        RecordEntity record21 = new RecordEntity(
                "MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER);
        RecordEntity record22 = new RecordEntity(
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
        List<RecordEntity> expected = new ArrayList<>();
        expected.add(new RecordEntity("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL));
        expected.add(new RecordEntity("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD));
        expected.add(new RecordEntity("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        expected.add(new RecordEntity("MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH));
        when(accountDataSource.read(path)).thenReturn(accountMap);
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        when(recordDataSource.read(path2)).thenReturn(recordMap2);
        List<RecordEntity>  actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll_Account_ID() throws DataSourceException, 
            DAOException {
        List<RecordEntity> expected = new ArrayList<>();
        expected.add(new RecordEntity("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL));
        expected.add(new RecordEntity("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD));
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        String accountId = "MrRobot@1583824237692";
        List<RecordEntity> actual = dao.getAll(accountId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DataSourceException, DAOException {
        RecordEntity expected = new RecordEntity(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        String id = "MrRobot@1583824237692@1583924900106";
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        RecordEntity actual = dao.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() throws DataSourceException, DAOException {
        String expected = "MrRobot@1583824237692@1583924900106";
        RecordEntity newRecord = new RecordEntity(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        
        String actual = dao.create(newRecord);
        verify(recordDataSource, times(1)).write(newRecord, path1);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() throws DataSourceException, DAOException {
        RecordEntity updatedRecord = new RecordEntity(
                "MrRobot@1583824237692@1583924900106", 
                new BigDecimal(250).setScale(2),ExpenseType.COMMUNAL);
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        boolean actual = dao.update(updatedRecord);
        verify(recordDataSource, times(1)).clearFile(path1);
        assertTrue(actual);
    }

    @Test
    public void testDelete() throws DataSourceException, DAOException {
        String id = "MrRobot@1583824237692@1583924900106";
        when(recordDataSource.read(path1)).thenReturn(recordMap1);
        boolean actual = dao.delete(id);
        verify(recordDataSource, times(1)).clearFile(path1);
        assertTrue(actual);
    }

}
