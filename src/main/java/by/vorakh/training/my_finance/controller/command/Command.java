package by.vorakh.training.my_finance.controller.command;

import by.vorakh.training.my_finance.controller.command.exception.CommandException;

public interface Command {
    
    public String execute(String request) throws CommandException;
    
}
