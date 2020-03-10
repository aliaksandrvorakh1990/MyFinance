package by.vorakh.training.my_finance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.convertor.impl.ExpenseRecordIdToAccountIdConvertor;
import by.vorakh.training.my_finance.dao.AccountDAO;
import by.vorakh.training.my_finance.dao.ExpenseRecordDAO;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.ExpenseRecordService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.IdValidator;

public class ExpenseRecordServiceImpl implements ExpenseRecordService,
        IdValidator {

    private AccountDAO accountDAO;
    private ExpenseRecordDAO expenseDAO;

    public ExpenseRecordServiceImpl(AccountDAO accountDAO,
            ExpenseRecordDAO expenseDAO) {
        this.accountDAO = accountDAO;
        this.expenseDAO = expenseDAO;
    }

    @Override
    public List<Record> getAll() throws ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute operation"
                    + " of all expense records reading:";
        try {
            return expenseDAO.getAll();
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public List<Record> getAll(String  accountId) throws
            ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute operation"
                + " of expense records reading by  account Id:";
        if (!isAccountId(accountId)) {
            String message = problem + "This is not Account Id";
            throw new ServiceException(message);
        }
        try {
            List<Record> accountExpenses = new ArrayList<Record>();
            Account account = accountDAO.getById(accountId);
            if (!isEqualsNull(account)) {
                accountExpenses.addAll(expenseDAO.getAll(account));
            }
            return accountExpenses;
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public List<Record> getAll(String  accountId, ExpenseType type)
            throws ServiceException  {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute operation"
                + " of expense records reading by account id and expense type:";
        if (!isAccountId(accountId)) {
            String message = problem + "This is not Account Id";
            throw new ServiceException(message);
        }
        if (isEqualsNull(type)) {
            String message = problem + "Expense type has null value";
            throw new ServiceException(message);
        }
        try {
            List<Record> accountExpenses =
                    new ArrayList<Record>();
            Account account = accountDAO.getById(accountId);
            if (!isEqualsNull(account)) {
                for (Record expense : getAll(accountId)) {
                   ExpenseType currentExpenseType = expense.getType();
                   if (currentExpenseType.equals(type)) {
                       accountExpenses.add(expense);
                   }
                }
            }
            return accountExpenses;
        } catch (ServiceException | DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Record getById(String id) throws ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute operation"
                + " of expense records reading:";
        if(!isExpenseRecordId(id)) {
            String message = problem + "This is not Expense Record Id";
            throw new ServiceException(message);
        }
        try {
            String accountId = new ExpenseRecordIdToAccountIdConvertor()
                    .converte(id);
            Account recordOwner = accountDAO.getById(accountId);
            if (isEqualsNull(recordOwner)) {
                String message = problem + "This record is not exist.";
                throw new ServiceException(message);
            }
            return expenseDAO.getById(id);
        } catch (DAOException | ConvertorException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public String create(Record object) throws ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute "
                + "ExpenseRecord creating operation:";
        if (isEqualsNull(object)) {
            String message = problem + "ExpenseRecord has null value";
            throw new ServiceException(message);
        }
        try {
            String response = null;
            String id = object.getId();
            if (!isAccountId(id)) {
            String message = problem + "ExpenseRecord has to contain "
                + "account id for creating";
                throw new ServiceException(message);
            }
            Account seletedAccount = accountDAO.getById(id);
            if (!isEqualsNull(seletedAccount)) {
                BigDecimal balanse =seletedAccount.getBalance();
                BigDecimal amount = object.getAmount();
                balanse = (object.getType().getIsExpense()) 
                        ? balanse.subtract(amount) 
                        : balanse.add(amount);
                seletedAccount.setBalance(balanse);
                accountDAO.update(seletedAccount);
                long creatingTime = new Date().getTime();
                String recordId = String.format("%sT%s", id,
                        creatingTime);
                object.setId(recordId);
                response = expenseDAO.create(object);
            }
           
            return response;
        } catch (DAOException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean update(Record object) throws ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute "
                + "ExpenseRecord updating operation:";
        if (isEqualsNull(object)) {
            String message = problem + "ExpenseRecord has null value";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            String expenseId = object.getId();
            if (!isExpenseRecordId(expenseId)) {
                String message = problem + "This is not Expense Record Id";
                throw new ServiceException(message);
            }
            String accountId = new ExpenseRecordIdToAccountIdConvertor()
                    .converte(expenseId);
            if (!isAccountId(accountId)) {
                String message = problem + "This is not Account Id";
                throw new ServiceException(message);
            }
            Account seletedAccount = accountDAO.getById(accountId);
            if (!isEqualsNull(seletedAccount)) {
                Record oldRecord = expenseDAO.getById(expenseId);
                BigDecimal balanse =seletedAccount.getBalance();
                BigDecimal oldAmount = oldRecord.getAmount();
                balanse = (oldRecord.getType().getIsExpense()) 
                        ? balanse.add(oldAmount)  
                        : balanse.subtract(oldAmount);
                BigDecimal newAmount = object.getAmount();
                balanse = (object.getType().getIsExpense()) 
                        ? balanse.subtract(newAmount) 
                        : balanse.add(newAmount);
                seletedAccount.setBalance(balanse);
                accountDAO.update(seletedAccount);
                response = expenseDAO.update(object);
            }
            return response;
        } catch (DAOException | ConvertorException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(String id) throws ServiceException {
        String problem = "[ExpenseRecordServiceImpl]Unable to execute "
                + "ExpenseRecord deleting operation:";
        if (isEqualsNull(id)) {
            String message = problem + "Expense Record id has null value";
            throw new ServiceException(message);
        }
        if (!isExpenseRecordId(id)) {
            String message = problem + "This is not Expense Record Id";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            String accountId = new ExpenseRecordIdToAccountIdConvertor()
                    .converte(id);
            if (!isAccountId(accountId)) {
                String message = problem + "This is not Account Id";
                throw new ServiceException(message);
            }
            Account seletedAccount = accountDAO.getById(accountId);
            if (!isEqualsNull(seletedAccount)) {
                Record deletedRecord = expenseDAO.getById(id);
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
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

}
