package by.vorakh.training.my_finance.controller.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isCorrect;
import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isCorrectRequestWithoutArgs;

import by.vorakh.training.my_finance.controller.Controller;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.controller.exception.ControllerException;

public class AdminController implements Controller {
    
    private AdminCommandProvider provider = new AdminCommandProvider();
    
    private final static char paramDelimeter = '#';

    @Override
    public String executeTask(String request) throws ControllerException {
        if (!isCorrect(request)) {
            String message = "Wrong Request";
            throw new ControllerException(message);
        }
        try {
            int beginIndex = request.indexOf(paramDelimeter);
            String commandName = request.substring(0, beginIndex);
            Command executionCommand = provider.getCommand(commandName);
            String requestArgs = (isCorrectRequestWithoutArgs(request)) ? null
                    : request.substring(beginIndex + 1);
            return executionCommand.execute(requestArgs);
        } catch (CommandException e) {
            String message = e.getMessage();
            throw new ControllerException(message, e);
        }
    }
    
}
