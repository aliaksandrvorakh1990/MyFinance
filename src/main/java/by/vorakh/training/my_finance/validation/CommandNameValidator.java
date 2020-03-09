package by.vorakh.training.my_finance.validation;

import java.util.Set;

import by.vorakh.training.my_finance.controller.CommandName;

public interface CommandNameValidator extends NotNullValidator {

    default boolean isCorrectCommandName(String str, Set<CommandName> commandNames) {
	    boolean isCorect = false;
	    if ((!isEqualsNull(str)) && (!isEqualsNull(str))) {
	        for (CommandName commandName : commandNames) {
		        if (str.equals(commandName.name())) {
                    isCorect = true;
                }
	        }
	    }
	    return isCorect;
    }
}
