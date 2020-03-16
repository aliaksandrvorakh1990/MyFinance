package by.vorakh.training.my_finance.validation.bean;

import by.vorakh.training.my_finance.bean.User;

public class UserValidator {
    
    private UserValidator() {}
    
    public static boolean isCorrectForWriting(User user) {
        return ((user != null) && ((user.getLogin() != null) 
                && (user.getPassword() != null) 
                && (user.getRole() != null)));
    }

}
