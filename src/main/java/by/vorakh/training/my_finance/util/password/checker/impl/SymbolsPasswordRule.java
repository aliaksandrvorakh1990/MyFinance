package by.vorakh.training.my_finance.util.password.checker.impl;

import by.vorakh.training.my_finance.util.password.checker.PasswordRule;

public class SymbolsPasswordRule implements PasswordRule {

    @Override
    public boolean checkRule(char[] password) {
        boolean isCorrectPassword = true;
        
        for (char symbol : password) {
            boolean isCorrectSymbol = isNumber(symbol) || isUpperCaseLetter(symbol) 
                    || isLowerCaseSymbol(symbol);
            if (!isCorrectSymbol) {
                isCorrectPassword = false;
                break;
            }
        }

        return isCorrectPassword;
    }
    
    private boolean isNumber(char symbol) {
        final int ZERO_SYMBOL = 48;
        final int NINE_SYMBOL = 57;
        boolean isCorrectNumber = (symbol >= ZERO_SYMBOL) && (symbol <= NINE_SYMBOL);
        return isCorrectNumber;
    }
    
    private boolean isUpperCaseLetter(char symbol) {
        final int UPPER_CASE_A = 65;
        final int UPPER_CASE_Z = 90;
        boolean isCorrectUpperCaseLetter = (symbol >= UPPER_CASE_A) && (symbol <= UPPER_CASE_Z);
        return isCorrectUpperCaseLetter;
    }
    
    private boolean isLowerCaseSymbol(char symbol) {
        final int LOWER_CASE_A = 97;
        final int LOWER_CASE_Z = 122;
        boolean isCorrectLowerCaseLetter = (symbol >= LOWER_CASE_A) && (symbol <= LOWER_CASE_Z);
        return isCorrectLowerCaseLetter;
    }

}
