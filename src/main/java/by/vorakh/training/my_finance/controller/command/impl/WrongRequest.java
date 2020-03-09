package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class WrongRequest implements Command, NotNullValidator {

    @Override
    public String execute(String request) throws CommandException {
        String defaultResponse = "Wrong Request";
        String reponse = (isEqualsNull(request)) ? defaultResponse : request;
        return reponse;
    }

}
