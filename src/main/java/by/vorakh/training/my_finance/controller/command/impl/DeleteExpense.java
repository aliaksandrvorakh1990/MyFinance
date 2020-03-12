package by.vorakh.training.my_finance.controller.command.impl;

import static  by.vorakh.training.my_finance.validation.request.RequestValidator.isSingleArgRequest;
import static by.vorakh.training.my_finance.validation.type.IdValidator.isRecordId;

import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class DeleteExpense implements Command {
    
    private RecordService service;
    private RequestToIdConvertor idConvertor;
    
    public DeleteExpense(RecordService service, RequestToIdConvertor 
            idConvertor) {
        this.service = service;
        this.idConvertor = idConvertor;
    }

    @Override
    public String execute(String request) throws CommandException {
        if (!isSingleArgRequest(request)) {
            String message =  "Request has to have one arg.";
            throw new CommandException(message);
        }
        try {
            String response = null;
            String id = idConvertor.converte(request);
            if (isRecordId(id)) {
                Boolean isDelete = service.deleteById(id);
                response = (isDelete == null) ? "THIS RECORD CANNOT DELETE, "
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
