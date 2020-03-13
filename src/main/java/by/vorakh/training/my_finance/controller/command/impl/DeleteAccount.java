package by.vorakh.training.my_finance.controller.command.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isSingleArgRequest;
import static by.vorakh.training.my_finance.validation.type.IdValidator.isAccountId;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class DeleteAccount implements Command {
    
    private RequestToIdConvertor idConvertor;
    private AccountService service;

    public DeleteAccount(AccountService service, 
            RequestToIdConvertor idConvertor) {
        this.service = service;
	    this.idConvertor = idConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        if (!isSingleArgRequest(request)) {
            String message = "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
	        String id = idConvertor.converte(request);
            if (isAccountId(id)) {
                Boolean isDelete =  service.deleteById(id);
                response = (isDelete == null) ? "THIS ACCOUNT CANNOT DELETE, "
                        + "IT DOES NOT EXIST" 
                        : isDelete.toString();
            }
            return response;
	    } catch (ConvertorException | ServiceException e) {
	        String message = e.getMessage();
            throw new CommandException(message, e);
	    }
    }
 
}
