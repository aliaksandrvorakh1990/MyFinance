package by.vorakh.training.my_finance.util.password.checker;

import java.util.List;

import by.vorakh.training.my_finance.util.password.checker.exception.PassCheckerException;

public class PasswordChecker {
    
    private List<PasswordRule> rules;
    
    public PasswordChecker(List<PasswordRule> rules) {
        this.rules = rules;
    }

    public boolean check(char[] password) throws PassCheckerException {
        if (password == null) {
            String message = "Unable to execute a password checking. Password has a null value.";
            throw new PassCheckerException(message);
        }
        
        boolean isCorrectPassword = true;
        
        for (PasswordRule rule : rules) {
            boolean isMatchOfRule = rule.checkRule(password);
            if (!isMatchOfRule) {
                isCorrectPassword = false;
                break;
            }
        }
        
        return isCorrectPassword;
    }

}
