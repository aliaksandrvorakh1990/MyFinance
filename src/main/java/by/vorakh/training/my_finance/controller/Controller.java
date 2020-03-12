package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.exception.ControllerException;

public interface Controller {

    public String executeTask(String request) throws ControllerException;
    
}
