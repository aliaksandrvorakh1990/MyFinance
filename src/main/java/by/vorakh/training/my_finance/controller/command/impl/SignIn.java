package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToUserConvertor;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class SignIn implements Command, NotNullValidator {
    
    
    private UserService userService;
	private  RequestToUserConvertor requestUserConvertor ;

	public SignIn(UserService loginService,
		    RequestToUserConvertor requestUserConvertor) {
	    this.userService = loginService;
	    this.requestUserConvertor = requestUserConvertor;
	}

	public UserService getUserService() {
	    return userService;
	}

	public RequestToUserConvertor getRequestUserConvertor() {
	    return requestUserConvertor;
	}

	@Override
	public String execute(String request) throws CommandException {
	    String problem ="Unable to excute SingIn Command:";
        if (isEqualsNull(request)) {
            String message = problem + "Request has null value.\n";
            throw new CommandException(message);
        }
		try {
			User user = requestUserConvertor.converte(request);
			UserRole serviceResponse = userService.singIn(user);
			return serviceResponse.name();
		} catch (ConvertorException | ServiceException e) {
			String message = problem + e.getMessage();
			throw new CommandException(message, e);
		} 
	}
	
}
