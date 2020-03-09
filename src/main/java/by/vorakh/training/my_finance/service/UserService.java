package by.vorakh.training.my_finance.service;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.service.entity.LoginResponse;
import by.vorakh.training.my_finance.service.exception.ServiceException;

public interface UserService extends Service<User, Integer> {

    LoginResponse singIn(User user) throws ServiceException; 
    
    LoginResponse singUp(User user) throws ServiceException;
    
}
