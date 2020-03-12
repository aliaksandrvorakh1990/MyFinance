package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.AccountEntityToCsvConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToAccountEntityConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityCsvDataSourceImplWriteTest {
    
    private AccountEntityCsvDataSourceImpl ds;
    private String path;
    private String expectedFilePath;
    private File expectedFile;
    private File actualFile;

    @Before
    public void init() throws IOException {
        Convertor<String,  AccountEntity> csvToEntityConvertor = 
                new CsvToAccountEntityConvertor();
        Convertor< AccountEntity, String> entitycsvToConvertor = 
                new AccountEntityToCsvConvertor();
        ds = new AccountEntityCsvDataSourceImpl(
                csvToEntityConvertor, entitycsvToConvertor);
        
        path = "src/test/resources/csv/data_source_impl/test.csv";
        expectedFilePath = "src/test/resources/csv/data_source_impl"
                + "/accounts.csv";
        expectedFile = new File(expectedFilePath);
        actualFile = new File(path);
        actualFile.createNewFile();
    }
    
    @After
    public void finalize() {
        actualFile.delete();
    }

    @Test
    public void testWrite_Single_AccountEntity() throws DataSourceException, 
            IOException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        AccountEntity entity = new AccountEntity(id, name, balance);
        ds.write(entity, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWrite_Collection_Of_AccountEntity() throws 
            DataSourceException, IOException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        AccountEntity entity = new AccountEntity(id, name, balance);
        Collection<AccountEntity> accounts = new ArrayList<AccountEntity>();
        accounts.add(entity);
        ds.write(accounts, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

}
