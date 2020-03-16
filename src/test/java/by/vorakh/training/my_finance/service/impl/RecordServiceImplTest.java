package by.vorakh.training.my_finance.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class RecordServiceImplTest {
    
    @Mock
    private AccountDAO accountDAO;
    @Mock()
    private RecordDAO expenseDAO;
    
    @InjectMocks
    private RecordServiceImpl service;
    
    private List<AccountEntity> accounts; 
    private List<Record> records1; 
    private List<Record> records2;
   
    
    @Before
    public void init() {
        accounts = new ArrayList<AccountEntity>();
        AccountEntity account1 = new AccountEntity("MrRobot@1583824237692", 
                "MyFirstAccount", new BigDecimal(100).setScale(2));
        AccountEntity account2 = new AccountEntity("MrXXX@1583996205058", 
                "Visa", new BigDecimal("150.99").setScale(2));
        accounts.add(account1);
        accounts.add(account2);
        records1 = new ArrayList<Record>();
        records2 = new ArrayList<Record>();
        Record record11 = new Record("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL);
        Record record12 = new Record("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD);
        records1.add(record11);
        records1.add(record12);
        Record record21 = new Record("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER);
        Record record22 = new Record("MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH);
        records2.add(record21);
        records2.add(record22);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetAll() throws DAOException, ServiceException {
        List<Record> expected = new ArrayList<Record>();
        expected.add(new Record("MrRobot@1583824237692@1583924900106", 
                new BigDecimal(25).setScale(2),ExpenseType.COMMUNAL));
        expected.add(new Record("MrRobot@1583824237692@1583926330227", 
                new BigDecimal(40).setScale(2),ExpenseType.FOOD));
        expected.add(new Record("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        expected.add(new Record("MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH));
        List<Record> daoResponse = new ArrayList<Record>(records1);
        daoResponse.addAll(records2);
        when(expenseDAO.getAll()).thenReturn(daoResponse);
        List<Record> actual = service.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll_AccoutnId() throws DAOException, ServiceException {
        List<Record> expected = new ArrayList<Record>();
        expected.add(new Record("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        expected.add(new Record("MrXXX@1583996205058@1584000954826", 
                new BigDecimal(45).setScale(2),ExpenseType.HEALTH));
        String id = "MrXXX@1583996205058";
        when(accountDAO.getById(id)).thenReturn(new AccountEntity(
                "MrXXX@1583996205058", "Visa", 
                new BigDecimal("150.99").setScale(2)));
        when(expenseDAO.getAll(id)).thenReturn(records2);
        List<Record> actual = service.getAll(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DAOException, ServiceException {
        Record expected = new Record("MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER);
        String id = "MrXXX@1583996205058@1584000734766";
        when(expenseDAO.getById(id)).thenReturn(new Record(
                "MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        Record actual = service.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteById() throws DAOException, ServiceException {
        String id = "MrXXX@1583996205058@1584000734766";
        when(expenseDAO.getById(id)).thenReturn(new Record(
                "MrXXX@1583996205058@1584000734766", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER));
        when(accountDAO.getById("MrXXX@1583996205058")).thenReturn(
                new AccountEntity("MrXXX@1583996205058", "Visa", 
                        new BigDecimal("150.99").setScale(2)));
        when(expenseDAO.delete(id)).thenReturn(true);
        Boolean actual = service.deleteById(id);
        assertTrue(actual);
    }

}
