package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.assertArrayEquals;

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
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordToCsvConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class RecordEntityCsvDataSourceImplWriteTest {
    
    private RecordCsvDataSourceImpl ds;
    private String path;
    private String expectedFilePath;
    private File expectedFile;
    private File actualFile;
    
    @Before
    public void init() throws IOException {
        Convertor<String, Record> csvToEntityConvertor = 
                new CsvToRecordConvertor();
        Convertor<Record, String> entitycsvToConvertor = 
                new RecordToCsvConvertor();
        ds = new RecordCsvDataSourceImpl(
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
    public void testWrite_OneRecord() throws DataSourceException, 
            IOException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        Record record = new Record(id, amount, type);
        ds.write(record, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWrite_CollectionOfRecord() throws DataSourceException, 
            IOException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        Record record = new Record(id, amount, type);
        Collection<Record> records = new ArrayList<Record>();
        records.add(record);
        ds.write(records, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

}
