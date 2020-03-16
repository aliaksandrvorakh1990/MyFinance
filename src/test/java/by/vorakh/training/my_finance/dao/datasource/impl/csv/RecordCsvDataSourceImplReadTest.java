package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordToCsvConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class RecordCsvDataSourceImplReadTest {
    
    private RecordCsvDataSourceImpl ds;
    
    @Before
    public void init() {
        Convertor<String, Record> csvToEntityConvertor = 
                new CsvToRecordConvertor();
        Convertor<Record, String> entityToCsvConvertor = 
                new RecordToCsvConvertor();
        ds = new RecordCsvDataSourceImpl(
                csvToEntityConvertor, entityToCsvConvertor);
    }

    @Test
    public void testRead() throws DataSourceException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        Record expectedRecord = new Record(id, amount, type);
        Map<String, Record> expected = 
                new LinkedHashMap<String, Record>();
        expected.put(id, expectedRecord);
        String path = "src/test/resources/csv/data_source_impl/records.csv";
        Map<String, Record> actual = ds.read(path);
        assertEquals(expected, actual);
    }

}
