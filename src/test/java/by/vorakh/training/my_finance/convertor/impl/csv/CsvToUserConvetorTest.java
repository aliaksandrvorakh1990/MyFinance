package by.vorakh.training.my_finance.convertor.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;

public class CsvToUserConvetorTest {

    @Test
    public void testConverte_Correct_CSV() throws ConvertorException {
        String login = "alex";
        String password = "3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f"
                + "978d7c846f4";
        UserRole role = UserRole.ADMIN;
        User expected = new User(login, password, role);
        String csv = "alex,3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f"
                + "978d7c846f4,ADMIN";
        CsvToUserConvetor convetor = new CsvToUserConvetor();
        User actual = convetor.converte(csv);
        assertEquals(expected, actual);
    }
    
    @Test(expected = ConvertorException.class)
    public void testConverte_Null_CSV() throws ConvertorException {
        String csv = null;
        CsvToUserConvetor convetor = new CsvToUserConvetor();
        User actual = convetor.converte(csv);
        assertNull(actual);
    }

}
