package by.vorakh.training.my_finance.util.password.checker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.util.password.checker.exception.PassCheckerException;
import by.vorakh.training.my_finance.util.password.checker.impl.LengthPasswordRule;
import by.vorakh.training.my_finance.util.password.checker.impl.SymbolsPasswordRule;

public class PasswordCheckerTest {
    
    private List<PasswordRule> rules;
    private PasswordChecker checker;
    
    @Before
    public void init() {
        rules = new ArrayList<PasswordRule>();
        rules.add(new LengthPasswordRule());
        rules.add(new SymbolsPasswordRule());
        checker = new PasswordChecker(rules);
    }

    @Test
    public void testCheck_Password_With_Four_Numbers_True() throws PassCheckerException {
        String passwordAsString = "9120";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheck_Password_With_Twelve_Letters_And_Numbers_True() throws PassCheckerException {
        String passwordAsString = "AZaz91xy2BC0";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheck_Password_With_Six_Letters_True() throws PassCheckerException {
        String passwordAsString = "AcYxBaZ";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheck_Password_With_Special_Symbols_False() throws PassCheckerException {
        String passwordAsString = "Ac#Yx@BaZ";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertFalse(actual);
    }
    
    @Test
    public void testCheck_Password_With_Russian_Letters_False() throws PassCheckerException {
        String passwordAsString = "Пароль";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertFalse(actual);
    }
    
    @Test
    public void testCheck_Password_With_Three_Letters__False() throws PassCheckerException {
        String passwordAsString = "AxZ";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertFalse(actual);
    }
    
    @Test
    public void testCheck_Password_With_Thirteen_Letters_And_Numbers_False() throws PassCheckerException {
        String passwordAsString = "A0x4Zb925er3W";
        char[] password = passwordAsString.toCharArray();
        boolean actual = checker.check(password);
        assertFalse(actual);
    }
    
    @Test(expected = PassCheckerException.class)
    public void testCheck_Password_With_Null_Value_TrownException() throws PassCheckerException {
        char[] password = null;
        boolean actual = checker.check(password);
        assertFalse(actual);
    }

}
