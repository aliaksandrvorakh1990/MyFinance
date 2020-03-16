package by.vorakh.training.my_finance.dao.impl.csv;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.vorakh.training.my_finance.dao.datasource.csv.AccountCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

@SuppressWarnings("unused")
public class CsvAccountDAOTest {
    
    @Mock
    private AccountCsvDataSource dataSource;
    
    @InjectMocks
    private CsvAccountDAO dao;
    
    private Map<String,AccountEntity> accountMap;
    private String path;
    
    @Before
    public void init() {
        accountMap = new LinkedHashMap<String, AccountEntity>();
        AccountEntity account1 = new AccountEntity("MrRobot@1583824237692", 
                "MyFirstAccount", new BigDecimal(100).setScale(2));
        AccountEntity account2 = new AccountEntity("MrXXX@1583996205058", 
                "Visa", new BigDecimal("150.99").setScale(2));
        AccountEntity account3 = new AccountEntity("MrXXX@1583998848244", 
                "BelCard", new BigDecimal("230.44").setScale(2));
        path = "./csv/accounts.csv";
        accountMap.put(account1.getId(), account1);
        accountMap.put(account2.getId(), account2);
        accountMap.put(account3.getId(), account3);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws DAOException, DataSourceException {
        List<AccountEntity> expected = new ArrayList<AccountEntity>();
        expected.add(new AccountEntity("MrRobot@1583824237692", "MyFirstAccount",
                new BigDecimal(100).setScale(2)));
        expected.add(new AccountEntity("MrXXX@1583996205058", "Visa", 
                new BigDecimal("150.99").setScale(2)));
        expected.add(new AccountEntity("MrXXX@1583998848244", "BelCard", 
                new BigDecimal("230.44").setScale(2)));
        when(dataSource.read(path)).thenReturn(accountMap);
        List<AccountEntity> actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUserID() throws DataSourceException, DAOException {
        List<AccountEntity> expected = new ArrayList<AccountEntity>();
        expected.add(new AccountEntity("MrXXX@1583996205058", "Visa", 
                new BigDecimal("150.99").setScale(2)));
        expected.add(new AccountEntity("MrXXX@1583998848244", "BelCard", 
                new BigDecimal("230.44").setScale(2)));
        String userId = "MrXXX";
        when(dataSource.read(path)).thenReturn(accountMap);
        List<AccountEntity> actual = dao.getAll(userId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DataSourceException, DAOException {
        AccountEntity expected = new AccountEntity("MrRobot@1583824237692", 
                "MyFirstAccount", new BigDecimal(100).setScale(2));
        String id = "MrRobot@1583824237692";
        when(dataSource.read(path)).thenReturn(accountMap);
        AccountEntity actual = dao.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() throws DataSourceException, DAOException {
        String expected = "MrXXX@1583999791975";
        AccountEntity newAccount = new AccountEntity("MrXXX@1583999791975", 
                "VISA", new BigDecimal("204.56").setScale(2));
        String actual = dao.create(newAccount);
        verify(dataSource, times(1)).write(newAccount, path);
        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdate() throws DataSourceException, DAOException {
        AccountEntity updatedAccount = new AccountEntity("MrXXX@1583996205058", 
                "Visa", new BigDecimal("150.99").setScale(2));
        when(dataSource.read(path)).thenReturn(accountMap);
        boolean actual = dao.update(updatedAccount);
        verify(dataSource, times(1)).clearFile(path);
        verify(dataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testDelete() throws DataSourceException, DAOException {
        String id = "MrXXX@1583996205058";
        when(dataSource.read(path)).thenReturn(accountMap);
        boolean actual = dao.delete(id);
        verify(dataSource, times(1)).clearFile(path);
        verify(dataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }

}
