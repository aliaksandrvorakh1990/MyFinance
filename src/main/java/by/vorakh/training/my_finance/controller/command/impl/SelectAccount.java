package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.output.AccountToTableOutputter;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;

public class SelectAccount implements Command, IdValidator, 
        AccountToTableOutputter {

    private RequestToIdConvertor convertor;
    private AccountService service;

    public SelectAccount(RequestToIdConvertor convertor, AccountService service) {
        this.convertor = convertor;
        this.service = service;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute SelectAccount Command:";
        if (!isSingleArgRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
            String id = convertor.converte(request);
            if (isAccountId(id)) {
                Account myAccount = service.getById(id);
                response = createTable(myAccount);
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
