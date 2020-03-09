package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.exception.ControllerException;
import by.vorakh.training.my_finance.validation.RequestValidator;

public interface Controller extends RequestValidator {

    public String executeTask(String request) throws ControllerException;
    
}
