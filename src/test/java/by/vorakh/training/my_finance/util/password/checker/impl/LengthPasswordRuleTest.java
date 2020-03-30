package by.vorakh.training.my_finance.util.password.checker.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LengthPasswordRuleTest {
    
    private LengthPasswordRule rule;
    
    @Before
    public void init() {
        rule = new LengthPasswordRule();
    }

    @Test
    public void testCheckRule_Length_Is_Four_True() {
        char[] password = new char[4];
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_Length_Is_Twelve_True() {
        char[] password = new char[12];
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_Length_Is_Six_True() {
        char[] password = new char[6];
        boolean actual = rule.checkRule(password);
        assertTrue(actual);
    }
    
    @Test
    public void testCheckRule_Length_Is_Three_False() {
        char[] password = new char[3];
        boolean actual = rule.checkRule(password);
        assertFalse(actual);
    }
    
    @Test
    public void testCheckRule_Length_Is_Thirteen_False() {
        char[] password = new char[13];
        boolean actual = rule.checkRule(password);
        assertFalse(actual);
    }
    
}
