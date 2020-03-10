package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.CurrencyValidator;
import by.vorakh.training.my_finance.validation.IdValidator;
import by.vorakh.training.my_finance.validation.RequestValidator;

public class CreateAccount implements Command, RequestValidator, IdValidator,
        CurrencyValidator {

    private RequestToAccountConvertor accountConvertor;
    private RequestToIdConvertor idConvertor ;
    private AccountService service ;

    protected CreateAccount() {}

    public CreateAccount(RequestToAccountConvertor accountConvertor,
            RequestToIdConvertor idConvertor, AccountService service) {
        this.accountConvertor = accountConvertor;
        this.idConvertor = idConvertor;
        this.service = service;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute SelectAccount Command:";
        if (isEqualsNull(request)) {
            String message = problem + "Request has null value.";
            throw new CommandException(message);
        }
        if (!isMultiArgsRequest(request)) {
            String message = problem + "Request has to have more than one arg.";
            throw new CommandException(message);
        }
        try {
            String userId = idConvertor.converte(request);
            Account account = accountConvertor.converte(request);
            account.setId(userId);
            String accountId = service.create(account);
            return accountId;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
