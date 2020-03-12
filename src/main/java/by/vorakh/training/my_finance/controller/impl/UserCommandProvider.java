package by.vorakh.training.my_finance.controller.impl;

import java.util.EnumMap;
import java.util.Map;

import by.vorakh.training.my_finance.controller.CommandName;
import by.vorakh.training.my_finance.controller.CommandProvider;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.controller.command.impl.CreateAccount;
import by.vorakh.training.my_finance.controller.command.impl.CreateExpense;
import by.vorakh.training.my_finance.controller.command.impl.DeleteAccount;
import by.vorakh.training.my_finance.controller.command.impl.DeleteExpense;
import by.vorakh.training.my_finance.controller.command.impl.Exit;
import by.vorakh.training.my_finance.controller.command.impl.MyAccounts;
import by.vorakh.training.my_finance.controller.command.impl.MyExpenses;
import by.vorakh.training.my_finance.controller.command.impl.SelectAccount;
import by.vorakh.training.my_finance.controller.command.impl.SelectExpense;
import by.vorakh.training.my_finance.controller.command.impl.WrongRequest;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToExpenseRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.request.RequestToIdConvertor;
import by.vorakh.training.my_finance.service.factory.ServiceFactory;


public class UserCommandProvider implements CommandProvider {
	
	private final Map<CommandName, Command> repository = 
			new EnumMap<>(CommandName.class);
	
	UserCommandProvider(){
		init();
	}

	private void init() {
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.EXIT, new Exit());
		repository.put(CommandName.MY_ACCOUNTS, new MyAccounts(
		        new ServiceFactory().getAccountService(), 
				new RequestToIdConvertor()));
		repository.put(CommandName.CREATE_ACCOUNT, new CreateAccount(
				new RequestToAccountConvertor(), new RequestToIdConvertor(),
				new ServiceFactory().getAccountService()));
		repository.put(CommandName.SELECT_ACCOUNT, new SelectAccount(
				new RequestToIdConvertor(), 
				new ServiceFactory().getAccountService()));
		repository.put(CommandName.DELETE_ACCOUNT, new DeleteAccount(
		        new ServiceFactory().getAccountService(), 
		        new RequestToIdConvertor()));
		repository.put(CommandName.MY_EXPENSES, new MyExpenses(
		        new ServiceFactory().getRecordService(), 
		        new RequestToIdConvertor()));
		repository.put(CommandName.SELECT_EXPENSE, new SelectExpense(
		        new ServiceFactory().getRecordService(), 
                new RequestToIdConvertor()));
		repository.put(CommandName.CREATE_EXPENSE, new CreateExpense(
		        new ServiceFactory().getRecordService(), 
                new RequestToIdConvertor(),
		        new RequestToExpenseRecordConvertor()));
		repository.put(CommandName.DELETE_EXPENSE, new DeleteExpense(
		        new ServiceFactory().getRecordService(), 
                new RequestToIdConvertor()));
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
