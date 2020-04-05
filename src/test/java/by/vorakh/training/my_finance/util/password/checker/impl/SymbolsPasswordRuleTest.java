package by.vorakh.training.my_finance.util.password.checker.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SymbolsPasswordRuleTest {
    
    private SymbolsPasswordRule rule;
    
    @Before
    public void init() {
        rule = new SymbolsPasswordRule();
    }

    @Test
    public void testCheckRule_Number_Password_True() {
        String passwordAsString = "1234567890";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_UpperCase_Letters_Password_True() {
        String passwordAsString = "ABCXYZ";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_LowerCase_Letters_Password_True() {
        String passwordAsString = "abcxyz";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_Mix_Correct_Password_True() {
        String passwordAsString = "ABC01xyz89";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_Password_With_Incorrect_Symbols_False() {
        String passwordAsString = "A_B@0#x%z(^*";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertFalse(actual);
    }
    
    @Test
    public void testCheckRule_Password_With_Russian_Letters_False() {
        String passwordAsString = "Пароль";
        char[] password = passwordAsString.toCharArray();
        boolean actual = rule.checkRule(password);
        assertFalse(actual);
    }

}
