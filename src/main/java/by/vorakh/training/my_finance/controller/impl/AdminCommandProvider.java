package by.vorakh.training.my_finance.controller.impl;

import java.util.EnumMap;
import java.util.Map;

import by.vorakh.training.my_finance.controller.CommandName;
import by.vorakh.training.my_finance.controller.CommandProvider;
import by.vorakh.training.my_finance.controller.command.Command;
import by.vorakh.training.my_finance.controller.command.exception.CommandException;
import by.vorakh.training.my_finance.controller.command.impl.Exit;
import by.vorakh.training.my_finance.controller.command.impl.WrongRequest;
import by.vorakh.training.my_finance.convertor.impl.AccountToStringConvertor;
import by.vorakh.training.my_finance.convertor.impl.ExpenseRecordToStringConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToAccountConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToExpenseRecordConvertor;
import by.vorakh.training.my_finance.convertor.impl.StringToUserConvertor;
import by.vorakh.training.my_finance.convertor.impl.UserToStringConvertor;
import by.vorakh.training.my_finance.dao.builder.DAOBuilder;
import by.vorakh.training.my_finance.dao.datasource.builder.DataSourceBuilder;
import by.vorakh.training.my_finance.dao.datasource.impl.AccountDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.ExpenseDataSourceImpl;
import by.vorakh.training.my_finance.dao.datasource.impl.UserDataSourceImpl;
import by.vorakh.training.my_finance.dao.impl.FileAccountDAO;
import by.vorakh.training.my_finance.dao.impl.FileExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.impl.FileUserDAO;
import by.vorakh.training.my_finance.service.builder.ServiceBuilder;
import by.vorakh.training.my_finance.service.impl.AccountServiceImpl;
import by.vorakh.training.my_finance.service.impl.ExpenseRecordServiceImpl;
import by.vorakh.training.my_finance.service.impl.UserServiceImpl;

public class AdminCommandProvider implements CommandProvider {

    private final Map<CommandName, Command> repository =
            new EnumMap<>(CommandName.class);

    private ServiceBuilder serviceBuilder;
    private DAOBuilder daoBuilder;
    private DataSourceBuilder dataSourceBuilder;

    AdminCommandProvider(){
        this.dataSourceBuilder = new DataSourceBuilder();
        dataSourceBuilder.setAccountDataSource(
                new AccountDataSourceImpl(new StringToAccountConvertor(),
                new AccountToStringConvertor()));
        dataSourceBuilder.setExpenseRecordDataSource(new ExpenseDataSourceImpl(
                new StringToExpenseRecordConvertor(),
                new ExpenseRecordToStringConvertor()));
        dataSourceBuilder.setUserDataSourse(new UserDataSourceImpl(
                new StringToUserConvertor(), new UserToStringConvertor()));
        this.daoBuilder = new DAOBuilder();
        daoBuilder.setExpenseRecordDAO(new FileExpenseRecordDAO(
                dataSourceBuilder.getExpenseRecordDataSource(),
                dataSourceBuilder.getAccountDataSource()));
        daoBuilder.setAccountDAO(new FileAccountDAO(
                dataSourceBuilder.getAccountDataSource(),
                daoBuilder.getExpenseRecordDAO()));
        daoBuilder.setUserDAO(new FileUserDAO(
                dataSourceBuilder.getUserDataSourse(),
                daoBuilder.getAccountDAO()));
        this.serviceBuilder = new ServiceBuilder();
        serviceBuilder.setExpenseRecordService(new ExpenseRecordServiceImpl(
            daoBuilder.getAccountDAO(), daoBuilder.getExpenseRecordDAO()));
        serviceBuilder.setAccountService(new AccountServiceImpl(
                daoBuilder.getAccountDAO(), daoBuilder.getUserDAO(),
                daoBuilder.getExpenseRecordDAO()));
        serviceBuilder.setUserService(new UserServiceImpl(
                daoBuilder.getUserDAO(), serviceBuilder.getAccountService()));
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
