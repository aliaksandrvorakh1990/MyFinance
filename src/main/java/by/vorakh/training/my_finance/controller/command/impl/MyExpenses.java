package by.vorakh.training.my_finance.controller.command.impl;

import java.util.List;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;
import by.vorakh.training.my_finance.view.output.ExpenseToTableOutputter;

public class MyExpenses implements Command, IdValidator, 
        ExpenseToTableOutputter {
    
    private ExpenseRecordService service;
    private RequestToIdConvertor convertor;
    
    protected MyExpenses() {}

    public MyExpenses(ExpenseRecordService service, RequestToIdConvertor convertor) {
        this.service = service;
        this.convertor = convertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute MyExpenses Command:";
        if (!isSingleArgRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
            String id = convertor.converte(request);
            if (isAccountId(id)) {
                List<Record> myRecords = service.getAll(id);
                response = createTable(myRecords);
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
