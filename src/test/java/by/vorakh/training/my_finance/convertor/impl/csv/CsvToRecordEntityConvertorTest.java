package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class CsvToRecordEntityConvertorTest {

    @Test
    public void testConverte() throws ConvertorException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        RecordEntity expected = new RecordEntity(id, amount, type);
        String csv = "MrRobot@1583824237692@1583824237692,123.24,CAR";
        CsvToRecordEntityConvertor convertor = new CsvToRecordEntityConvertor();
        RecordEntity actual = convertor.converte(csv);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Csv_ThrownException() throws 
            ConvertorException {
        String csv = null;
        CsvToRecordEntityConvertor convertor = new CsvToRecordEntityConvertor();
        RecordEntity actual = convertor.converte(csv);
        assertNull(actual);
    }

}
