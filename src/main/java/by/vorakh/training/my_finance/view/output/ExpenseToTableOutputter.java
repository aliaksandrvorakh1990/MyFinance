package by.vorakh.training.my_finance.view.output;

import java.math.BigDecimal;
import java.util.List;

import by.vorakh.training.my_finance.bean.ExpenseRecord;
import by.vorakh.training.my_finance.bean.ExpenseType;
import by.vorakh.training.my_finance.validation.NotNullValidator;

public interface ExpenseToTableOutputter extends NotNullValidator {
    
    public final static String FORMAT = "|%-45s|%-15s|%-10s|%n";
    
    default String createTable(ExpenseRecord record) {
        String head =  String.format(FORMAT, "ID", "Amount", "Type");
        StringBuffer sb = new StringBuffer(head);
        if (!isEqualsNull(record)) {
            String id = record.getId();
            ExpenseType type = record.getType();
            BigDecimal amount = record.getAmount();
            sb.append(String.format(FORMAT, id, amount, type));
        }
        return sb.toString();
    }
    
    default String createTable(List<ExpenseRecord> records) {
        String head =  String.format(FORMAT, "ID", "Amount", "Type");
        StringBuffer sb = new StringBuffer(head);
        
        if (!isEqualsNull(records)) {
            for (ExpenseRecord record : records) {
                String id = record.getId();
                ExpenseType type = record.getType();
                BigDecimal amount = record.getAmount();
                sb.append(String.format(FORMAT, id, amount, type));
            }
        }
        return sb.toString();
    }

}
