package by.vorakh.training.my_finance.validation;

import by.vorakh.training.my_finance.bean.UserRole;

public interface UserValidator {

    default boolean isCorrectLogin(String login) {
        String regex ="^[a-zA-Z][a-zA-Z\\d]{3,19}?";
        return ((login != null) && (login.matches(regex)));
    }

    default boolean isCorrectPassword(String password) {
        String regex ="[a-zA-Z\\d]{4,12}?";
        return ((password != null) && (password.matches(regex)));
    }

    default boolean isEncrytpedPassword(String password) {
        String regex ="[a-f\\d]{63,64}?";
        return ((password != null) && (password.matches(regex)));
    }

    default boolean isCorrectRole(String str) {
        boolean isCorrect = false;
        if (str != null) {
            for (UserRole role : UserRole.values()) {
                if (str.equals(role.name())) {
                    isCorrect = true;
                    break;
                }
            }
        }
        return isCorrect;
    }
}
