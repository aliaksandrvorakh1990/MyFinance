package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.LoginResponseStringConvertor;
import by.vorakh.training.my_finance.convertor.impl.RequestToUserConvertor;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.entity.LoginResponse;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public class SignIn implements Command, NotNullValidator {
    
    
    private UserService userService;
	private LoginResponseStringConvertor  loginResponseConvertor;
	private  RequestToUserConvertor requestUserConvertor ;
	
	protected SignIn() {}

	public SignIn(UserService loginService, 
	        LoginResponseStringConvertor loginResponseConvertor,
		    RequestToUserConvertor requestUserConvertor) {
	    this.userService = loginService;
	    this.loginResponseConvertor = loginResponseConvertor;
	    this.requestUserConvertor = requestUserConvertor;
	}

	public UserService getUserService() {
	    return userService;
	}

	public LoginResponseStringConvertor getLoginResponseConvertor() {
	    return loginResponseConvertor;
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
			LoginResponse serviceResponse = userService.singIn(user);
			String response = loginResponseConvertor.converte(serviceResponse);
			return response;
		} catch (ConvertorException e) {
			String message = "Convertor";
			throw new CommandException(message, e);
		} catch (ServiceException e) {
			String message = "Service";
			throw new CommandException(message, e);
		}
	}
	
}
