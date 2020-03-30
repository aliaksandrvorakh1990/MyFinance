package by.vorakh.training.my_finance.util.password.crypto;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import by.vorakh.training.my_finance.util.password.crypto.exception.CryptoException;

public class Sha256HasherTest {

    @Test
    public void testGetSHA_Contain_Hex_Symbols_True() throws CryptoException {
        String passwordAsString = "AZaz91xy2BC0";
        char[] password = passwordAsString.toCharArray();
        String encryptedPassword = Sha256Hasher.getSHA(password);
        String regex ="[a-f\\d]{64}";
        boolean actual = encryptedPassword.matches(regex);
        assertTrue(actual);
    }

    @Test
    public void testGetSHA_PasswordLenght_64() throws CryptoException {
        int expected = 64;
        String passwordAsString = "AZaz91xy2BC0";
        char[] password = passwordAsString.toCharArray();
        String encryptedPassword = Sha256Hasher.getSHA(password);
        int actual = encryptedPassword.length();
        assertEquals(expected, actual);;
    }
    
    @Test(expected = CryptoException.class)
    public void testGetSHA_Password_Has_Null_Value_ThrowException() throws CryptoException {
        char[] password = null;
        String actual = Sha256Hasher.getSHA(password);
        assertNull(actual);
    }

}
