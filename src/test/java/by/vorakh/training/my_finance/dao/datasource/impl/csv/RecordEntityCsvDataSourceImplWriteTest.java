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

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordEntityConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordEntityToCsvConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityCsvDataSourceImplWriteTest {
    
    private RecordEntityCsvDataSourceImpl ds;
    private String path;
    private String expectedFilePath;
    private File expectedFile;
    private File actualFile;
    
    @Before
    public void init() throws IOException {
        Convertor<String, RecordEntity> csvToEntityConvertor = 
                new CsvToRecordEntityConvertor();
        Convertor<RecordEntity, String> entitycsvToConvertor = 
                new RecordEntityToCsvConvertor();
        ds = new RecordEntityCsvDataSourceImpl(
                csvToEntityConvertor, entitycsvToConvertor);
        path = "src/test/resources/csv/data_source_impl/test.csv";
        expectedFilePath = "src/test/resources/csv/data_source_impl"
                + "/records.csv";
        expectedFile = new File(expectedFilePath);
        actualFile = new File(path);
        actualFile.createNewFile();
    }
    
    @After
    public void finalize() {
        actualFile.delete();
    }

    @Test
    public void testWrite_OneRecordEntity() throws DataSourceException, 
            IOException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        RecordEntity record = new RecordEntity(id, amount, type);
        ds.write(record, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWrite_CollectionOfRecordEntity() throws DataSourceException, 
            IOException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        RecordEntity record = new RecordEntity(id, amount, type);
        Collection<RecordEntity> records = new ArrayList<RecordEntity>();
        records.add(record);
        ds.write(records, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

}
