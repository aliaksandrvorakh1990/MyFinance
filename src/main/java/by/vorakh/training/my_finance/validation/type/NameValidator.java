package by.vorakh.training.my_finance.validation.type;

import java.util.Set;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.controller.CommandName;

public interface NameValidator {

    default boolean isCorrectAccountName(String str) {
        String regex ="[\\w]+";
        return ((str != null) && (str.matches(regex)));
    }

    default boolean isCorrectLogin(String login) {
        String regex ="^[a-zA-Z][a-zA-Z\\d]{3,19}?";
        return ((login != null) && (login.matches(regex)));
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
    
    default boolean isCorrectExpenseType(String str) {
        boolean isCorrect = false;
        if (str != null) {
            for (ExpenseType type : ExpenseType.values()) {
                if (str.equals(type.name())) {
                    isCorrect = true;
                    break;
                }
            }
        }
        return isCorrect;
    }
    
    default boolean isCorrectCommandName(String str, 
            Set<CommandName> commandNames) {
        boolean isCorect = false;
        if ((str != null) && (commandNames != null)) {
            for (CommandName commandName : commandNames) {
                if (str.equals(commandName.name())) {
                    isCorect = true;
                    break;
                }
            }
        }
        return isCorect;
    }
}
