package by.vorakh.training.my_finance.validation;

import by.vorakh.training.my_finance.bean.ExpenseType;

public interface ExpenseTypeValidator {
    
    default boolean isCorrectExpenseType(String str) {
        boolean isCorrect = false;
        if (str != null) {
            for (ExpenseType type : ExpenseType.values()) {
                if (str.equals(type.name())) {
                    isCorrect = true;
                    break;
                }
            }
        }
        return isCorrect;
    }

}
