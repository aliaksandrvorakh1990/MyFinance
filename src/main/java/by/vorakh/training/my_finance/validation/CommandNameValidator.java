package by.vorakh.training.my_finance.validation;

import java.util.Set;

import by.vorakh.training.my_finance.controller.CommandName;

public interface CommandNameValidator {

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
