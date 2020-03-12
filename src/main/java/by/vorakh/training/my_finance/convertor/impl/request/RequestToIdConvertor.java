package by.vorakh.training.my_finance.convertor.impl.request;

import static by.vorakh.training.my_finance.convertor.exception.ConvertorException.PROBLEM;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.validation.RequestValidator;

public class RequestToIdConvertor implements Convertor<String, String>, RequestValidator{

    private static final String REGEX = "&";
    private static final char ARG_DELIMETER = '=';
    private static final String ID = "ID";

    @Override
    public String converte(String object) throws ConvertorException {
        if (object == null) {
            String message = "[RequestUserConvertor]" + PROBLEM  + "Request "
                    + "has null value.";
            throw new ConvertorException(message);
        }
        String id = null;
        if (isSingleArgRequest(object)) {
            id = extractId(object);
        } else if (isMultiArgsRequest(object)){
            String[] args = object.split(REGEX);
            for (String arg : args) {
                if (arg.startsWith(ID)) {
                    id = extractId(arg);
                    break;
                }
            }
        }
        return id;
    }

    private String extractId(String str) {
        int beginIndex = str.indexOf(ARG_DELIMETER);
        String argName = str.substring(0, beginIndex);
        String argValue = str.substring(beginIndex + 1);
        String id = (argName.equals(ID)) ? argValue : null;
        return id;
    }

}
