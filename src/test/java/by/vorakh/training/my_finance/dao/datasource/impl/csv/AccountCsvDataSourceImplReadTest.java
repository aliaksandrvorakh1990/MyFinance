package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.AccountToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToAccountConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class AccountCsvDataSourceImplReadTest {
    
    private AccountCsvDataSourceImpl ds;
    private String path;

    @Before
    public void init() throws IOException {
        Convertor<String, Account> csvToAccountConvertor = 
                new CsvToAccountConvertor();
        Convertor< Account, String> accountToCsvConvertor = 
                new AccountToCsvConvertor();
        ds = new AccountCsvDataSourceImpl(
                csvToAccountConvertor, accountToCsvConvertor);
        path = "src/test/resources/csv/data_source_impl"
                + "/accounts.csv";
    }
    
    @Test
    public void testRead() throws DataSourceException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        Account entity = new Account(id, name, balance);
        Map<String, Account> expected = 
                new LinkedHashMap<String, Account>();
        expected.put(id, entity);
        Map<String, Account> actual = ds.read(path);
        assertEquals(expected, actual);
    }

}
