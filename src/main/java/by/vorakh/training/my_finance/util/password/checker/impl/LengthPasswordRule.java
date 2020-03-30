package by.vorakh.training.my_finance.util.password.checker.impl;

import by.vorakh.training.my_finance.util.password.checker.PasswordRule;

public class LengthPasswordRule implements PasswordRule{
    
    private final static int MIN_LENGTH = 4;
    private final static int MAX_LENGTH = 12;

    @Override
    public boolean checkRule(char[] password) {
        int passwordLenght = password.length;
        boolean isCorrect = (passwordLenght >= MIN_LENGTH) && (passwordLenght <= MAX_LENGTH);
        return isCorrect;
    }

}
