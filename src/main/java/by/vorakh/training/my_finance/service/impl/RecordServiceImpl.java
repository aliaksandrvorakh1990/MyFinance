package by.vorakh.training.my_finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.RecordDAO;
import by.vorakh.training.my_finance.dao.entity.AccountEntity;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.RecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.type.IdValidator;

public class RecordServiceImpl implements RecordService,
        IdValidator {

    private AccountDAO accountDAO;
    private RecordDAO expenseDAO;
    private Convertor<RecordEntity, Record> entityConvertor;
    private Convertor<Record, RecordEntity> beanConvertor;

    public RecordServiceImpl(AccountDAO accountDAO, RecordDAO expenseDAO,
            Convertor<RecordEntity, Record> entityConvertor, 
            Convertor<Record, RecordEntity> beanConvertor) {
        this.accountDAO = accountDAO;
        this.expenseDAO = expenseDAO;
        this.entityConvertor = entityConvertor;
        this.beanConvertor = beanConvertor;
    }

    @Override
    public List<Record> getAll() throws ServiceException {
        try {
            return expenseDAO.getAll().stream()
                    .collect(Collectors.mapping(recordEntity -> 
                            entityConvertor.converte(recordEntity), 
                            Collectors.toList()));
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public List<Record> getAll(String  accountId) throws
            ServiceException {
        if (!isAccountId(accountId)) {
            String message = "This is not Account Id";
            throw new ServiceException(message);
        }
        try {
            List<Record> accountExpenses = new ArrayList<Record>();
            AccountEntity account = accountDAO.getById(accountId);
            if (account != null) {
                Stream<RecordEntity> recordEntities = expenseDAO
                        .getAll(accountId).stream();
                List<Record> foundRecords = recordEntities.collect(
                        Collectors.mapping(recordEntity -> 
                                entityConvertor.converte(recordEntity), 
                                Collectors.toList()));
                        accountExpenses.addAll(foundRecords);
            }
            return accountExpenses;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public List<Record> getAll(String accountId, ExpenseType type)
            throws ServiceException {
        if (!isAccountId(accountId) || (type == null)) {
            String message = "This is not Account Id or Expense type "
                    + "has null value";
            throw new ServiceException(message);
        }
        try {
            List<Record> accountExpenses = new ArrayList<Record>();
            AccountEntity account = accountDAO.getById(accountId);
            if (account != null) {
                Stream<RecordEntity> recordEntities = expenseDAO
                        .getAll(accountId).stream();
                List<Record> foundRecords = recordEntities
                        .filter(record -> record.getType().equals(type))
                        .collect(Collectors.mapping(recordEntity -> 
                                entityConvertor.converte(recordEntity), 
                                Collectors.toList()));
                        accountExpenses.addAll(foundRecords);
            }
            return accountExpenses;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Record getById(String id) throws ServiceException {
        if(!isRecordId(id)) {
            String message = "This is not Expense Record Id";
            throw new ServiceException(message);
        }
        try {
            return entityConvertor.converte(expenseDAO.getById(id));
        } catch (DAOException | ConvertorException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public String create(Record object) throws ServiceException {
        if (object == null) {
            String message = "ExpenseRecord has null value";
            throw new ServiceException(message);
        }
        try {
            String response = null;
            String id = object.getId();
            AccountEntity seletedAccount = accountDAO.getById(id);
            if (seletedAccount != null) {
                BigDecimal balanse =seletedAccount.getBalance();
                BigDecimal amount = object.getAmount();
                balanse = (object.getType().getIsExpense()) 
                        ? balanse.subtract(amount) 
                        : balanse.add(amount);
                seletedAccount.setBalance(balanse);
                accountDAO.update(seletedAccount);
                long creatingTime = new Date().getTime();
                String recordId = String.format("%s@%s", id,
                        creatingTime);
                object.setId(recordId);
                response = expenseDAO.create(beanConvertor.converte(object));
            }
            return response;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }
    
    @Override
    public Boolean deleteById(String id) throws ServiceException {
        if (!isRecordId(id)) {
            String message = "This is not Expense Record Id";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            String accountId = getAccountIdFrom(id);
            AccountEntity seletedAccount = accountDAO.getById(accountId);
            if (seletedAccount != null) {
                RecordEntity deletedRecord = expenseDAO.getById(id);
                BigDecimal balanse =seletedAccount.getBalance();
                BigDecimal amount = deletedRecord.getAmount();
                balanse = (deletedRecord.getType().getIsExpense()) 
                        ? balanse.add(amount)  
                        : balanse.subtract(amount);
                seletedAccount.setBalance(balanse);
                accountDAO.update(seletedAccount);
                response = expenseDAO.delete(id);
            }
            return response;
        } catch (DAOException | ConvertorException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }
    
    private String getAccountIdFrom(String recordId) {
        int lastDelimeter = recordId.lastIndexOf("@");
        return recordId.substring(0, lastDelimeter);
    }

}
