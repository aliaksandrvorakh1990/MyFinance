package by.vorakh.training.my_finance.controller.command.impl;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.RequestToExpenseRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;

public class CreateExpense implements Command, IdValidator {

    private ExpenseRecordService service;
    private RequestToIdConvertor idConvertor;
    private RequestToExpenseRecordConvertor expenseConvertor;
    
    protected CreateExpense() {}
    
    public CreateExpense(ExpenseRecordService service, 
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
            String response = null;
            Record newExpenseRecord = expenseConvertor.converte(request);
            String id = idConvertor.converte(request);
            if (isAccountId(id) && !isEqualsNull(newExpenseRecord)) {
                newExpenseRecord.setId(id);
                response = service.create(newExpenseRecord);
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
