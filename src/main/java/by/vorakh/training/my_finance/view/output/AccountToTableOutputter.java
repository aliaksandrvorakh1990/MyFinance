package by.vorakh.training.my_finance.view.output;
import java.math.BigDecimal;
import java.util.List;

import by.vorakh.training.my_finance.bean.Account;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public interface AccountToTableOutputter extends NotNullValidator {
    
    public final static String FORMAT = "|%-30s|%-20s|%-15s|%-10s|%n";
    
    default String createTable(Account account) {
        String head =  String.format(FORMAT, "ID","Name", "Balance", "Records");
        StringBuffer sb = new StringBuffer(head);
        if (!isEqualsNull(account)) {
            String id = account.getId();
            String name = account.getName();
            BigDecimal balance = account.getBalance();
            int recordsSize = account.getExpenses().size();
            sb.append(String.format(FORMAT, id, name, balance, recordsSize));
        }
        return sb.toString();
    }
    
    default String createTable(List<Account> accounts) {
        String head =  String.format(FORMAT, "ID","Name", "Balance", "Records");
        StringBuffer sb = new StringBuffer(head);
        
        if (!isEqualsNull(accounts)) {
            for (Account account : accounts) {
                String id = account.getId();
                String name = account.getName();
                BigDecimal balance = account.getBalance();
                int recordsSize = account.getExpenses().size();
                sb.append(String.format(FORMAT, id, name, balance, recordsSize));
            }
        }
        return sb.toString();
    }

}
