package by.vorakh.training.my_finance.controller.command.exception;

public class CommandException extends Exception {

    private static final long serialVersionUID = 6336508780700546103L;

    public CommandException(String message, Throwable ex) {
        super(message, ex);
    }

    public CommandException(String message) {
        super(message);
    }
    
}
