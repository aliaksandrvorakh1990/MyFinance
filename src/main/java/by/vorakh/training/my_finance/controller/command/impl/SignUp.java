package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToUserConvertor;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class SignUp implements Command {

    private UserService userService;
    private  RequestToUserConvertor requestUserConvertor ;

    public SignUp(UserService userService, 
            RequestToUserConvertor requestUserConvertor) {
        this.userService = userService;
        this.requestUserConvertor = requestUserConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute SingUp Command:";
        if (request == null) {
            String message = problem + "Request has null value.\n";
            throw new CommandException(message);
        }
        try {
            User newUser = requestUserConvertor.converte(request);
            newUser.setRole(UserRole.USER);
            UserRole serviceResponse = userService.singUp(newUser);
            return serviceResponse.name();
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
