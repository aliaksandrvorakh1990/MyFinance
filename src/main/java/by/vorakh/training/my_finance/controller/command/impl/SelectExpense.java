package by.vorakh.training.my_finance.controller.command.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isSingleArgRequest;
import static by.vorakh.training.my_finance.validation.type.IdValidator.*;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.output.ExpenseToTableOutputter;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class SelectExpense implements Command, ExpenseToTableOutputter {
    
    private RecordService service;
    private RequestToIdConvertor convertor;

    public SelectExpense(RecordService service, RequestToIdConvertor convertor) {
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
            if (isRecordId(id)) {
                Record myRecord = service.getById(id);
                response = (myRecord == null) ? "RECORD DOES NOT EXIST" 
                        : createTable(myRecord);
            }
            return response;
        } catch (ConvertorException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new CommandException(message, e);
        }
    }

}
