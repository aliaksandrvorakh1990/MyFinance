package by.vorakh.training.my_finance.controller.impl;

import java.util.EnumMap;
import java.util.Map;

import by.vorakh.training.my_finance.controller.CommandName;
import by.vorakh.training.my_finance.controller.CommandProvider;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.controller.command.impl.Exit;
import by.vorakh.training.my_finance.controller.command.impl.SignIn;
import by.vorakh.training.my_finance.controller.command.impl.SignUp;
import by.vorakh.training.my_finance.controller.command.impl.WrongRequest;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToUserConvertor;
import by.vorakh.training.my_finance.service.factory.ServiceFactory;

public class LoginCommandProvider implements CommandProvider {

    private final Map<CommandName, Command> repository =
            new EnumMap<>(CommandName.class);

    LoginCommandProvider() {
        init();
    }

    private void init() {
        repository.put(CommandName.SIGN_IN, new SignIn(
                new ServiceFactory().getUserService(),
                new RequestToUserConvertor()));
        repository.put(CommandName.SIGN_UP, new SignUp(
                new ServiceFactory().getUserService(),
                new RequestToUserConvertor()));
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.EXIT, new Exit());
    }

    @Override
    public Command getCommand(String name) {
        Command command = null;
        try {
            if (!isCorrectCommandName(name, repository.keySet())) {
                throw new CommandException("This command is not existing");
            }
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (CommandException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

}
