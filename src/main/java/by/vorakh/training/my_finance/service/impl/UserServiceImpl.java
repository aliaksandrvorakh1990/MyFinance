package by.vorakh.training.my_finance.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.exception.BeanFillingException;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;
    private AccountService accountService;

    public UserServiceImpl(UserDAO userDAO, AccountService accountService) {
        this.userDAO = userDAO;
        this.accountService = accountService;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll().stream()
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public User getById(String id) throws ServiceException {
        if (id == null) {
            String message ="id has null value";
            throw new ServiceException(message);
        }
        try {
            User user = userDAO.getById(id);
            if (user != null) {
                fillBean(user);
            }
            return user;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public UserRole singIn(User user) throws ServiceException {
        if (user == null) {
            String message = "User has null value.";
            throw new ServiceException(message);
        }
        try {
            UserRole role  = null;
            String login = user.getLogin();
            String password = user.getPassword();
            User foundUser = userDAO.getById(login);
            if (foundUser != null) { 
                String foundUserPassoword = foundUser.getPassword();
                if (password.equals(foundUserPassoword)) {
                    role = foundUser.getRole();
                }
            }
            return role;
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public UserRole singUp(User user) throws ServiceException {
        if (user == null) {
            String message ="User has null value.";
            throw new ServiceException(message);
        }
        try {
            UserRole response = null;
            String id = create(user);
            if (id != null) {
                response = user.getRole();
            } 
            return response;
        } catch (ServiceException e) {
            String message = e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public String create(User object) throws ServiceException {
        if (object == null) {
            String message = "User has null value";
            throw new ServiceException(message);
        }
        String login = object.getLogin();
        String password = object.getPassword();
        try {
            String response = null;
            object.setPassword(password);
            boolean isContainLogin = userDAO.getById(login) != null;
            if (!isContainLogin) {
                response = userDAO.create(object);
                String accountId = object.getLogin();
                String accoutnName = new String("MyFirstAccount");
                BigDecimal startBalance = new BigDecimal(0).
                        setScale(2, BigDecimal.ROUND_HALF_UP);
                Account newAccount = new Account(accountId, accoutnName,
                        startBalance);
                accountService.create(newAccount);
            }
            return response;
        } catch (DAOException | ServiceException e) {
                String message = e.getMessage();
                throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(String id) throws ServiceException {
        if (id == null) {
            String message = "User has null value";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            User deletedUser = getById(id);
            boolean isContain = deletedUser != null;
            if (isContain) {
                List<Account> userAccounts = deletedUser.getAccounts();
                if (userAccounts != null) {
                    for (Account account : userAccounts) {
                        accountService.deleteById(account.getId());
                    }
                }
                response = userDAO.delete(id);
            }
            return response;
        } catch (DAOException | ServiceException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }
    
    private void fillBean(User user) {
        try {
            String id = user.getLogin();
            List<Account> userAccounts = accountService
                    .getAll(id);
            user.setAccounts(userAccounts);
        } catch (ServiceException e) {
            String message = e.getMessage();
            throw new BeanFillingException(message, e);
        }
        
    }

}
