package by.vorakh.training.my_finance.view.output;

import java.util.List;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public interface UserToTableOutputter extends NotNullValidator {
    
public final static String FORMAT = "|%-20s|%-20s|%-10s|%n";
    
    default String createTable(User user) {
        String head =  String.format(FORMAT, "ID", "Login", "Role");
        StringBuffer sb = new StringBuffer(head);
        if (!isEqualsNull(user)) {
            Integer id = user.getId();
            String login = user.getLogin();
            UserRole role = user.getRole();
            sb.append(String.format(FORMAT, id, login, role));
        }
        return sb.toString();
    }
    
    default String createTable(List<User> users) {
        String head =  String.format(FORMAT, "ID", "Login", "Role");
        StringBuffer sb = new StringBuffer(head);
        if (!isEqualsNull(users)) {
            for (User user : users) {
                Integer id = user.getId();
                String login = user.getLogin();
                UserRole role = user.getRole();
                sb.append(String.format(FORMAT, id, login, role));
            }
        }
        return sb.toString();
    }

}
