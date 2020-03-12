package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;
import by.vorakh.training.my_finance.validation.RequestValidator;

public class DeleteAccount implements Command, RequestValidator, IdValidator {
    
    private RequestToIdConvertor idConvertor;
    private AccountService service;

    public DeleteAccount(AccountService service, 
            RequestToIdConvertor idConvertor) {
        this.service = service;
	    this.idConvertor = idConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
	    String problem ="Unable to excute SelectAccount Command:";
	    if (request == null) {
	        String message = problem + "Request has null value.";
	        throw new CommandException(message);
	    }
        if (!isSingleArgRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
	        String id = idConvertor.converte(request);
            if (isAccountId(id)) {
                Boolean isDelete =  service.deleteById(id);
                response = (isDelete == null) ? null 
                        : isDelete.toString();
            }
            return response;
	    } catch (ConvertorException | ServiceException e) {
	        String message = problem + e.getMessage();
            throw new CommandException(message, e);
	    }
    }
 
}
