package by.vorakh.training.my_finance.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

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

public class RecordServiceImplCreateTest {
    
    @Mock
    private AccountDAO accountDAO;
    @Mock()
    private RecordDAO expenseDAO;

    @InjectMocks
    private RecordServiceImpl service;
    
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate() throws DAOException, ServiceException {
        String expected = "MrXXX@1583996205058@1584000734766";
        Record newRecord = new Record("MrXXX@1583996205058", 
                new BigDecimal(140).setScale(2),ExpenseType.OTHER);
        when(accountDAO.getById("MrXXX@1583996205058")).thenReturn(
                new AccountEntity("MrXXX@1583996205058", "Visa", 
                        new BigDecimal("150.99").setScale(2)));
        when(expenseDAO.create(any(Record.class)))
                .thenReturn("MrXXX@1583996205058@1584000734766");
        String actual = service.create(newRecord);
        assertEquals(expected, actual);
    }

}
