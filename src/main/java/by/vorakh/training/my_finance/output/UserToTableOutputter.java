package by.vorakh.training.my_finance.output;

import java.util.List;

import by.vorakh.training.my_finance.bean.User;
import by.vorakh.training.my_finance.bean.UserRole;

public interface UserToTableOutputter {
    
public final static String FORMAT = "|%-20s|%-10s|%n";
    
    default String createTable(User user) {
        String head =  String.format(FORMAT, "Login", "Role");
        StringBuffer sb = new StringBuffer(head);
        if (user != null) {
            String login = user.getLogin();
            UserRole role = user.getRole();
            sb.append(String.format(FORMAT, login, role));
        }
        return sb.toString();
    }
    
    default String createTable(List<User> users) {
        String head =  String.format(FORMAT, "Login", "Role");
        StringBuffer sb = new StringBuffer(head);
        if (users != null) {
            for (User user : users) {
                String login = user.getLogin();
                UserRole role = user.getRole();
                sb.append(String.format(FORMAT, login, role));
            }
        }
        return sb.toString();
    }

}
