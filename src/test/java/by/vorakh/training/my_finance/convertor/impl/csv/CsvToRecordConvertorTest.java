package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToRecordConvertorTest {

    @Test
    public void testConverte() throws ConvertorException {
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        Record expected = new Record(id, amount, type);
        String csv = "MrRobot@1583824237692@1583824237692,123.24,CAR";
        CsvToRecordConvertor convertor = new CsvToRecordConvertor();
        Record actual = convertor.converte(csv);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Csv_ThrownException() throws 
            ConvertorException {
        String csv = null;
        CsvToRecordConvertor convertor = new CsvToRecordConvertor();
        Record actual = convertor.converte(csv);
        assertNull(actual);
    }

}
