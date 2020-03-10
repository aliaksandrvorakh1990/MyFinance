package by.vorakh.training.my_finance.service;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public interface UserService extends Service<User, String> {

    UserRole singIn(User user) throws ServiceException; 
    
    UserRole singUp(User user) throws ServiceException;
    
}
