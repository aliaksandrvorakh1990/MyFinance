package by.vorakh.training.my_finance.view;

import java.io.Console;
import java.util.Arrays;

import by.vorakh.training.my_finance.controller.CommandName;
import by.vorakh.training.my_finance.controller.ContollerStrategy;
import by.vorakh.training.my_finance.controller.impl.AdminController;
import by.vorakh.training.my_finance.controller.impl.LoginController;
import by.vorakh.training.my_finance.controller.impl.UserContoller;

public class Main {

    private final static String ADMIN = "ADMIN";
    private final static String USER = "USER";
    private final static String SIGN_IN = "SIGN_IN";
    private final static String SIGN_UP = "SIGN_UP";
    private final static String EXIT = "Exit";
    
    private static ContollerStrategy mainContoller = new ContollerStrategy();

    public static void main (String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("No console. \nRun application in terminal");
            System.exit(1);
        }
        selectMode(console);
        String response = null;
        do {
            String command = console.readLine("Enter command with arguments: ");
            response = mainContoller.executeTask(command);
            console.format("%s%n",response);
        } while(!response.equals(EXIT));
        doExit(console);
    } 
    
    private static void selectMode(Console console) {
        String role ="";
        String response = null;
        while (true) {
            selectController(role);
            if (role.equals(USER) || role.equals(ADMIN)) {
                console.format("You are identified%nYour role is %s%n", role);
                break;
            }
            String command = console.readLine("Enter command: ");
            switch (command) {
                case SIGN_IN:
                    response = doSignIn(console);
                    break;
                case SIGN_UP:
                    response = doSignUp(console);
                    break;
                case "EXIT":
                    doExit(console);
                    break;
                default:
                    console.printf("Wrong command for logining.%n");
            }
            if (response != null) {
                role = response;
            }
        }
    }
    
    private static String doSignIn(Console console) {
        String login = console.readLine("Enter your login: ");
        String password = new String(console
                .readPassword("Enter your password: "));
        String command = CommandName.SIGN_IN.name();
        String request = String
                .format("%s#LOGIN=%s&PASSWORD=%s",command, login, password);
        String response = mainContoller.executeTask(request);
        return response;
    }
    
    private static String doSignUp(Console console) {
        String login = console.readLine("Enter your login: ");
        String password = enterPasswords(console);
        String command = CommandName.SIGN_UP.name();
        String request = String
                .format("%s#LOGIN=%s&PASSWORD=%s",command, login, password);
        String response =  mainContoller.executeTask(request);
        return response;
    }
    
    private static String enterPasswords(Console console) {
        String password = null;
        boolean noMatch;
        do {
            char [] newPassword1 = console
                    .readPassword("Enter your password: ");
            char [] newPassword2 = console
                    .readPassword("Enter password again: ");
            noMatch = ! Arrays.equals(newPassword1, newPassword2);
            if (noMatch) {
                console.format("Passwords don't match. Try again.%n");
            } else {
                password = new String(newPassword1);
                console.format("Passwords are matched.%n");
            }
        } while (noMatch);
        
        return password;
    }
    
    private static void doExit(Console console) {
        console.printf("GoodBye\n");
        System.exit(1);
    }
    
    private static void selectController(String role) {
        switch(role) {
            case ADMIN:
                mainContoller.setContoller(new AdminController());
                break;
            case USER:
                mainContoller.setContoller(new UserContoller());
                break;
            default:
                mainContoller.setContoller(new LoginController());
        }
    }

}
