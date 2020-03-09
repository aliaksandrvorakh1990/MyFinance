package by.vorakh.training.my_finance.convertor.impl;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.service.entity.LoginResponse;

public class LoginResponseStringConvertor implements
        Convertor<LoginResponse, String> {

    @Override
    public String converte(LoginResponse object) throws ConvertorException {
        String response = (object == null) ? null : new StringBuffer()
            .append(object.getId())
            .append('|')
            .append(object.getRole())
            .toString();
        return response;
    }

}
