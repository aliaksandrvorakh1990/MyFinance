package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;

public class WrongRequest implements Command {

    @Override
    public String execute(String request) throws CommandException {
        String defaultResponse = "Wrong Request";
        String reponse = (request == null) ? defaultResponse : request;
        return reponse;
    }

}
