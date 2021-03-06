package by.vorakh.training.my_finance.controller.command.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isMultiArgsRequest;
import static by.vorakh.training.my_finance.validation.type.IdValidator.*;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToExpenseRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class CreateExpense implements Command {

    private RecordService service;
    private RequestToIdConvertor idConvertor;
    private RequestToExpenseRecordConvertor expenseConvertor;
    
    public CreateExpense(RecordService service, 
            RequestToIdConvertor idConvertor,
            RequestToExpenseRecordConvertor expenseConvertor) {
        this.service = service;
        this.idConvertor = idConvertor;
        this.expenseConvertor = expenseConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute CreateExpense Command:";
        if (!isMultiArgsRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String serviceResponse = null;
            Record newExpenseRecord = expenseConvertor.converte(request);
            String id = idConvertor.converte(request);
            if (isAccountId(id)) {
                newExpenseRecord.setId(id);
                serviceResponse = service.create(newExpenseRecord);
            }
            String response = (serviceResponse == null) 
                    ? "RECORD DOES NOT CREATED" 
                    : String.format("YOUR RECORD ID: %s", serviceResponse);
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
