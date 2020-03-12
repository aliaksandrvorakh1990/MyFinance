package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.validation.type.NameValidator;

public interface CommandProvider extends NameValidator {

    Command getCommand(String name);

}
