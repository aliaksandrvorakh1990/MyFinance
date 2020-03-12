package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.exception.ControllerException;

public class ContollerStrategy {

    private Controller contoller;

    public ContollerStrategy() {}

    public void setContoller(Controller contoller) {
        this.contoller = contoller;
    }

    public String executeTask(String request) {
        String response = null;
            try {
                response = this.contoller.executeTask(request);
        } catch (ControllerException e) {
            response ="This command is impossible to execute";
        }
        return response;
    }

}
