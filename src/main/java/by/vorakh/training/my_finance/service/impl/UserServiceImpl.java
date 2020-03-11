package by.vorakh.training.my_finance.service.impl;

import java.math.BigDecimal;
import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.crypto.Sha256Hasher;
import by.vorakh.training.my_finance.crypto.exception.CryptoException;
import by.vorakh.training.my_finance.dao.UserDAO;
import by.vorakh.training.my_finance.dao.exception.DAOException;
import by.vorakh.training.my_finance.service.AccountService;
import by.vorakh.training.my_finance.service.UserService;
import by.vorakh.training.my_finance.service.exception.ServiceException;
import by.vorakh.training.my_finance.validation.UserValidator;

public class UserServiceImpl implements UserService, UserValidator, Sha256Hasher {

    private UserDAO userDAO ;
    private AccountService accountService;

    public UserServiceImpl(UserDAO userDAO, AccountService accountService) {
        this.userDAO = userDAO;
        this.accountService = accountService;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public User getById(String id) throws ServiceException {
        if (isEqualsNull(id)) {
            String message ="id has null value";
            throw new ServiceException(message);
        }
        try {
            return userDAO.getById(id);
        } catch (DAOException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public UserRole singIn(User user) throws ServiceException {
        if (isEqualsNull(user)) {
            String message = "User has null value.";
            throw new ServiceException(message);
        }
        try {
            UserRole role  = null;
            String login = user.getLogin();
            String password = user.getPassword();
            User foundUser = getById(login);
            if (!isEqualsNull(foundUser)) {
                String encryptedPassword = getSHA(password);
                String foundUserPassoword = foundUser.getPassword();
                if (encryptedPassword.equals(foundUserPassoword)) {
                    role = foundUser.getRole();
                }
            }
            return role;
        } catch (ServiceException | CryptoException e) {
            String message = e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public UserRole singUp(User user) throws ServiceException {
        if (isEqualsNull(user)) {
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
        if (isEqualsNull(object)) {
            String message = "User has null value";
            throw new ServiceException(message);
        }
        String login = object.getLogin();
        String password = object.getPassword();
        UserRole userRole = object.getRole();
        try {
            String response = null;
            String encryptedPassword = getSHA(password);
            object.setPassword(encryptedPassword);
            boolean isContainLogin = !isEqualsNull(getById(login));
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
        } catch (DAOException | ServiceException | CryptoException e) {
                String message = e.getMessage();
                throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(String id) throws ServiceException {
        if (isEqualsNull(id)) {
            String message = "User has null value";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            User deletedUser = getById(id);
            boolean isContain = !isEqualsNull(deletedUser);
            if (isContain) {
                List<Account> userAccounts = deletedUser.getAccounts();
                if (!isEqualsNull(userAccounts)) {
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

}
