package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.validation.CommandNameValidator;

public interface CommandProvider extends CommandNameValidator {

    Command getCommand(String name);

}
