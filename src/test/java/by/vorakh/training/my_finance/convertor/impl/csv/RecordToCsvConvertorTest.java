package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class RecordToCsvConvertorTest {

    @Test
    public void testConverte_Correct_Record() throws ConvertorException {
        String expected = "MrRobot@1583824237692@1583824237692,123.24,CAR";
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        Record record = new Record(id, amount, type);
        RecordToCsvConvertor convertor = new RecordToCsvConvertor();
        String actual = convertor.converte(record);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Record_ThrownException() throws 
            ConvertorException {
        Record record = null;
        RecordToCsvConvertor convertor = new RecordToCsvConvertor();
        String actual = convertor.converte(record);
        assertNull(actual);
    }

}
