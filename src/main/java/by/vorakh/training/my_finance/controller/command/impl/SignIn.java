package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToUserConvertor;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class SignIn implements Command {

    private UserService userService;
    private  RequestToUserConvertor requestUserConvertor ;

    public SignIn(UserService loginService,
            RequestToUserConvertor requestUserConvertor) {
        this.userService = loginService;
        this.requestUserConvertor = requestUserConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        if (!isMultiArgsRequest(request)) {
            String message = "Wrong request";
            throw new CommandException(message);
        }
        try {
            User user = requestUserConvertor.converte(request);
            UserRole serviceResponse = userService.singIn(user);
            String response = (serviceResponse == null)
                    ? "Unable to excute SingIn: user with this login does not "
                            + "contain in system."
                    : serviceResponse.name();
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
