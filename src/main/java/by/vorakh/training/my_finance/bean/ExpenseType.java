package by.vorakh.training.my_finance.bean;

public enum ExpenseType {
    INCOME(false),
    COMMUNAL(true),
    FOOD(true),
    HEALTH(true),
    CAR(true),
    RELAX(true),
    PERSONAL(true),
    OTHER(true);
    
    private boolean isExpense;
    
    private ExpenseType(boolean isExpense) {
        this.isExpense = isExpense;
    }
    
    public boolean getIsExpense() {
        return isExpense;
    }
}
