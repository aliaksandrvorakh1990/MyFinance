package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class AccountToCsvConvertorTest {

    @Test
    public void testConverte_Correct_Account() throws ConvertorException {
        String expected = "MrRobot@1583824237692,MyFirstAccount,100.00";
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        Account entity = new Account(id, name, balance);
        AccountToCsvConvertor convertor = new AccountToCsvConvertor();
        String actual  = convertor.converte(entity);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_Account_ThrownException() throws 
            ConvertorException {
        Account entity = null;
        AccountToCsvConvertor convertor = new AccountToCsvConvertor();
        String actual  = convertor.converte(entity);
        assertNull(actual);
    }

}
