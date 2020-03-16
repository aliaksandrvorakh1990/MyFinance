package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToAccountConvertorTest {

    @Test
    public void testConverte_Correct_Csv() throws ConvertorException {
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        Account expected = new Account(id, name, balance);
        String csv = "MrRobot@1583824237692,MyFirstAccount,100.00";
        CsvToAccountConvertor convertor = 
                new CsvToAccountConvertor();
        Account actual = convertor.converte(csv);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Csv_ThrowException() throws 
            ConvertorException {
        String csv = null;
        CsvToAccountConvertor convertor = 
                new CsvToAccountConvertor();
        Account actual = convertor.converte(csv);
        assertNull(actual);
    }

}
