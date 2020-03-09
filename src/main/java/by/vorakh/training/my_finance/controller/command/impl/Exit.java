package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;

public class Exit implements Command {
    
    public Exit() {}

    @Override
    public String execute(String request) throws CommandException {
        return "Exit";
    }

}
