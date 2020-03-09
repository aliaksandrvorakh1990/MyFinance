package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;

public class SelectExpense implements Command, IdValidator {
    
    private ExpenseRecordService service;
    private RequestToIdConvertor convertor;
    
    protected SelectExpense() {}

    public SelectExpense(ExpenseRecordService service, RequestToIdConvertor convertor) {
        this.service = service;
        this.convertor = convertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        String problem ="Unable to excute SelectExpense Command:";
        if (!isSingleArgRequest(request)) {
            String message = problem + "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
            String id = convertor.converte(request);
            if (isExpenseRecordId(id)) {
                ExpenseRecord myRecord = service.getById(id);
                response = myRecord.toString();
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
