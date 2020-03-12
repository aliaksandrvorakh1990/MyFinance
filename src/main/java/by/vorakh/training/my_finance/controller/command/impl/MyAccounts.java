package by.vorakh.training.my_finance.controller.command.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isSingleArgRequest;

import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.output.AccountToTableOutputter;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class MyAccounts implements Command, AccountToTableOutputter {

    private AccountService service;
    private RequestToIdConvertor convertor;

    public MyAccounts(AccountService service, RequestToIdConvertor convertor) {
        this.service = service;
        this.convertor = convertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        if (!isSingleArgRequest(request)) {
            String message = "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String id = convertor.converte(request);
            List<Account> myAccounts = service.getAll(id);
            String response = (myAccounts.isEmpty()) ? "NO ACCOUNTS" 
                    : createTable(myAccounts);
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
