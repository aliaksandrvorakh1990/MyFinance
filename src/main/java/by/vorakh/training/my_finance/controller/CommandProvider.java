package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.command.Command;

public interface CommandProvider {

    Command getCommand(String name);

}
