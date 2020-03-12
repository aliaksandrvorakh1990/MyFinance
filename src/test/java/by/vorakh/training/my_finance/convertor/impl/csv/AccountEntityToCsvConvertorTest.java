package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;

public class AccountEntityToCsvConvertorTest {

    @Test
    public void testConverte_Correct_AccountEntity() throws ConvertorException {
        String expected = "MrRobot@1583824237692,MyFirstAccount,100.00";
        String id = "MrRobot@1583824237692";
        String name = "MyFirstAccount";
        BigDecimal balance = new BigDecimal(100.00)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        AccountEntity entity = new AccountEntity(id, name, balance);
        AccountEntityToCsvConvertor convertor = new AccountEntityToCsvConvertor();
        String actual  = convertor.converte(entity);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_AccountEntity_ThrownException() throws 
            ConvertorException {
        AccountEntity entity = null;
        AccountEntityToCsvConvertor convertor = new AccountEntityToCsvConvertor();
        String actual  = convertor.converte(entity);
        assertNull(actual);
    }

}
