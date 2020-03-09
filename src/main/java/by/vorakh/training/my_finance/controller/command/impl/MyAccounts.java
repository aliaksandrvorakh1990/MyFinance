package by.vorakh.training.my_finance.controller.command.impl;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;
import by.vorakh.training.my_finance.view.output.AccountToTableOutputter;

public class MyAccounts implements Command, IdValidator, AccountToTableOutputter {

    private AccountService service;
    private RequestToIdConvertor convertor;
    
    protected MyAccounts() {}

    public MyAccounts(AccountService service, RequestToIdConvertor convertor) {
	    this.service = service;
        this.convertor = convertor;
    }
    
    public AccountService getService() {
        return service;
    }

    public RequestToIdConvertor getConvertor() {
        return convertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute MyAccounts Command:";
        if (!isSingleArgRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
            String id = convertor.converte(request);
            if (isUserId(id)) {
                List<Account> myAccounts = service.getAll(id);
                response = createTable(myAccounts);
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
