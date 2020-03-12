package by.vorakh.training.my_finance.controller.impl;

import static by.vorakh.training.my_finance.validation.type.NameValidator.isCorrectCommandName;

import java.util.EnumMap;
import java.util.Map;

import by.vorakh.training.my_finance.controller.CommandName;
import by.vorakh.training.my_finance.controller.CommandProvider;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.controller.command.impl.Exit;
import by.vorakh.training.my_finance.controller.command.impl.WrongRequest;

public class AdminCommandProvider implements CommandProvider {

    private final Map<CommandName, Command> repository =
            new EnumMap<>(CommandName.class);

    AdminCommandProvider(){
        init();
    }

    private void init() {
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
