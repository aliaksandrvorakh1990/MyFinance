package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.AccountEntityToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToAccountEntityConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityCsvDataSourceImplReadTest {
    
    private AccountEntityCsvDataSourceImpl ds;
    private String path;

    @Before
    public void init() throws IOException {
        Convertor<String,  AccountEntity> csvToEntityConvertor = 
                new CsvToAccountEntityConvertor();
        Convertor< AccountEntity, String> entitycsvToConvertor = 
                new AccountEntityToCsvConvertor();
        ds = new AccountEntityCsvDataSourceImpl(
                csvToEntityConvertor, entitycsvToConvertor);
        path = "src/test/resources/csv/data_source_impl"
                + "/accounts.csv";
    }
    
    @Test
    public void testRead() throws DataSourceException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        AccountEntity entity = new AccountEntity(id, name, balance);
        Map<String, AccountEntity> expected = 
                new LinkedHashMap<String, AccountEntity>();
        expected.put(id, entity);
        Map<String, AccountEntity> actual = ds.read(path);
        assertEquals(expected, actual);
    }

}
