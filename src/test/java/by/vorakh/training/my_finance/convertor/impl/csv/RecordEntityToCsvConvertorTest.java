package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityToCsvConvertorTest {

    @Test
    public void testConverte_Correct_Record() throws ConvertorException {
        String expected = "MrRobot@1583824237692@1583824237692,123.24,CAR";
        String id = "MrRobot@1583824237692@1583824237692";
        BigDecimal amount = new BigDecimal(123.24)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        ExpenseType type = ExpenseType.CAR;
        RecordEntity record = new RecordEntity(id, amount, type);
        RecordEntityToCsvConvertor convertor = new RecordEntityToCsvConvertor();
        String actual = convertor.converte(record);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Record_ThrownException() throws 
            ConvertorException {
        RecordEntity record = null;
        RecordEntityToCsvConvertor convertor = new RecordEntityToCsvConvertor();
        String actual = convertor.converte(record);
        assertNull(actual);
    }

}
