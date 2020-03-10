package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class CsvToAccountEntityConvertorTest {

    @Test
    public void testConverte_Correct_Csv() throws ConvertorException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        AccountEntity expected = new AccountEntity(id, name, balance);
        String csv = "MrRobot@1583824237692,MyFirstAccount,100.00";
        CsvToAccountEntityConvertor convertor = 
                new CsvToAccountEntityConvertor();
        AccountEntity actual = convertor.converte(csv);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Csv_ThrowException() throws 
            ConvertorException {
        String csv = null;
        CsvToAccountEntityConvertor convertor = 
                new CsvToAccountEntityConvertor();
        AccountEntity actual = convertor.converte(csv);
        assertNull(actual);
    }

}
