package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToRecordEntityConvertor;
import by.vorakh.training.my_finance.convertor.impl.csv.RecordEntityToCsvConvertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityCsvDataSourceImplReadTest {
    
    private RecordEntityCsvDataSourceImpl ds;
    
    @Before
    public void init() {
        Convertor<String, RecordEntity> csvToEntityConvertor = 
                new CsvToRecordEntityConvertor();
        Convertor<RecordEntity, String> entityToCsvConvertor = 
                new RecordEntityToCsvConvertor();
        ds = new RecordEntityCsvDataSourceImpl(
                csvToEntityConvertor, entityToCsvConvertor);
    }

    @Test
    public void testRead() throws DataSourceException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        RecordEntity expectedRecord = new RecordEntity(id, amount, type);
        Map<String, RecordEntity> expected = 
                new LinkedHashMap<String, RecordEntity>();
        expected.put(id, expectedRecord);
        String path = "src/test/resources/csv/data_source_impl/records.csv";
        Map<String, RecordEntity> actual = ds.read(path);
        assertEquals(expected, actual);
    }

}
