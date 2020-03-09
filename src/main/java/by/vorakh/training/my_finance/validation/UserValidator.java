package by.vorakh.training.my_finance.validation;

import by.vorakh.training.my_finance.bean.UserRole;

public interface UserValidator extends NotNullValidator {

    default boolean isCorrectLogin(String login) {
        String regex ="^[a-zA-Z][a-zA-Z\\d]{3,19}?";
        return ((!isEqualsNull(login)) && (login.matches(regex)));
    }

    default boolean isIdOFThisLogin(String login, Integer id) {
        boolean isCorrect = false;
        if((!isEqualsNull(login)) && (!isEqualsNull(id))) {
            Integer loginHash = login.hashCode();
            isCorrect =  id.equals(loginHash);
        }
        return isCorrect;
    }

    default boolean isCorrectPassword(String password) {
        String regex ="[a-zA-Z\\d]{4,12}?";
        return ((!isEqualsNull(password)) && (password.matches(regex)));
    }

    default boolean isEncrytpedPassword(String password) {
        String regex ="[a-f\\d]{63,64}?";
        return ((!isEqualsNull(password)) && (password.matches(regex)));
    }

    default boolean isCorrectRole(String str) {
        boolean isCorrect = false;
        if (!isEqualsNull(str)) {
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
