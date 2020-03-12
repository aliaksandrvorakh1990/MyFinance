package by.vorakh.training.my_finance.controller.command;

import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.validation.request.RequestValidator;

public interface Command extends RequestValidator {
    
    public String execute(String request) throws CommandException;
    
}
