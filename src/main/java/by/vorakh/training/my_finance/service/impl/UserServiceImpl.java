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
import by.vorakh.training.my_finance.service.entity.LoginResponse;
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
            String problem = "[UserServiceImpl]Unable to execute user reading "
                    + "operation:";
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public User getById(Integer id) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute user reading "
                + "operation using id:";
        if (isEqualsNull(id)) {
            String message = problem + "id has null value";
            throw new ServiceException(message);
        }
        try {
            return userDAO.getById(id);
        } catch (DAOException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public LoginResponse singIn(User user) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute sign in "
                + "operation:";
        if (isEqualsNull(user)) {
            String message = problem + "User has null value.";
            throw new ServiceException(message);
        }
        try {
            LoginResponse response = null;
            String login = user.getLogin();
            if (!isCorrectLogin(login)) {
                String message = problem + "Login has wrong format or null."
                        + "value.";
                throw new ServiceException(message);
            }
            String password = user.getPassword();
            if (!isCorrectPassword(password)) {
                String message = problem + "Password has wrong format or null."
                        + "value.";
                throw new ServiceException(message);
            }
            User foundUser = getById(login.hashCode());
            if (!isEqualsNull(foundUser)) {
                String encryptedPassword = getSHA(password);
                String  foundUserPassoword = foundUser.getPassword();
                Integer id = foundUser.getId();
                UserRole role = foundUser.getRole();
                if((encryptedPassword.equals(foundUserPassoword))
                    && (!isEqualsNull(id)) && (!isEqualsNull(role))) {
                    response = new LoginResponse(id, role);
                }
            }
            return response;
        } catch (ServiceException | CryptoException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    public LoginResponse singUp(User user) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute sign up "
                + "operation:";
        if (isEqualsNull(user)) {
            String message = problem + "User has null value.";
            throw new ServiceException(message);
        }
        try {
            Integer id = create(user);
            UserRole role = user.getRole();
            LoginResponse response = (isEqualsNull(id)) ? null
                    : new LoginResponse(id, role);
            return response;
        } catch (ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message);
        }
    }

    @Override
    public Integer create(User object) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute user creating "
                + "operation:";
        if (isEqualsNull(object)) {
            String message = problem + "User has null value";
            throw new ServiceException(message);
        }
        String login = object.getLogin();
        if (!isCorrectLogin(login)) {
            String message = problem + "Login has wrong format or null value";
            throw new ServiceException(message);
        }
        if (!isIdOFThisLogin(login, object.getId())) {
            String message = problem + "ID is not for this login.";
            throw new ServiceException(message);
        }
        String password = object.getPassword();
        if (!isCorrectPassword(password)) {
            String message = problem + "Password has null value or uncorrect "
                + "format";
            throw new ServiceException(message);
        }
        UserRole userRole = object.getRole();
        if (isEqualsNull(userRole)) {
            String message = problem + "Role has null value";
            throw new ServiceException(message);
        }
        try {
            String encryptedPassword = getSHA(password);
            object.setPassword(encryptedPassword);
            boolean isContainLogin = false;
            for (User user : getAll()) {
                if(login.equals(user.getLogin())) {
                    isContainLogin = true;
                    break;
                }
            }
            Integer id = null;
            if (!isContainLogin) {
            id = userDAO.create(object);
                String accountId = object.getId().toString();
                String accoutnName = new String("MyFirstAccount");
                BigDecimal startBalance = new BigDecimal(0).
                        setScale(2, BigDecimal.ROUND_HALF_UP);
                accountService.create(new Account(accountId, accoutnName,
                    startBalance));
            }
            return id;
        } catch (DAOException | ServiceException | CryptoException e) {
                String message = problem + e.getMessage();
                throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean update(User object) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute user updating "
                + "operation:";
        if (isEqualsNull(object)) {
            String message = problem + "User has null value";
            throw new ServiceException(message);
        }
        try {
            Boolean response = null;
            Integer id = object.getId();
            boolean isContain = isEqualsNull(getById(id)) ;
            if (isContain) {
                List<Account> userAccounts = object.getAccounts();
                if (!isEqualsNull(userAccounts)) {
                    for (Account account : userAccounts) {
                    accountService.update(account);
                    }
                }
                response = userDAO.update(object);
            }
            return response;
        } catch (DAOException | ServiceException e) {
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

    @Override
    public Boolean deleteById(Integer id) throws ServiceException {
        String problem = "[UserServiceImpl]Unable to execute user deleting "
                + "operation:";
        if (isEqualsNull(id)) {
            String message = problem + "User has null value";
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
            String message = problem + e.getMessage();
            throw new ServiceException(message, e);
        }
    }

}
