package by.vorakh.training.my_finance.controller;

import by.vorakh.training.my_finance.controller.exception.ControllerException;

public class ControllerStrategy {

    private Controller controller;

    public ControllerStrategy() {}

    public void setContoller(Controller controller) {
        this.controller = controller;
    }

    public String executeTask(String request) {
        String response = null;
            try {
                response = this.controller.executeTask(request);
        } catch (ControllerException e) {
            response ="This command is impossible to execute";
        }
        return response;
    }

}
