package by.vorakh.training.my_finance.bean;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account implements Serializable {
    
    private static final long serialVersionUID = -8025723822579428024L;
    
    private String id;
    private String name;
    private BigDecimal balance;
    private List<Record> expenses;
    
    public Account() {}

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(String name, BigDecimal balance, 
            List<Record> expenses) {
        this.name = name;
        this.balance = balance;
        this.expenses = expenses;
    }
    
    public Account(String id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(String id, String name, BigDecimal balance, 
            List<Record> expenses) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.expenses = expenses;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Record> getExpenses() {
        return expenses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public void setExpenses(List<Record> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return String.format("%s [id=%s, name=%s, balance=%s, expenses=%s]",
                getClass().getName(), id, name, balance,expenses);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result =prime * result + ((expenses == null) ? 0 : expenses.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account other = (Account) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false; 
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }   
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (balance == null) {
            if (other.balance != null) {
                    return false;
            }
        } else if (balance.compareTo(other.balance) != 0) {
            return false;
        }
        if (expenses == null) {
            if (other.expenses != null) {
                return false;
            }
        } else if (!equals(expenses,other.expenses)) {
            return false;
        }
        return true;
    }
    
    private static boolean equals(List<Record> listA, List<Record> listB) {
        boolean isEquals = false;
        if (listA!=null && listB!=null) {
            int sizeA = listA.size();
            int sizeB = listB.size();
            if (sizeA == sizeB) {
                Record[] arrA= listA.toArray(new Record[sizeA]);
                ArrayList<Record>  copyA = new ArrayList<>(Arrays.asList(arrA));
                copyA.removeAll(listB);
                if (copyA.isEmpty()) {
                    isEquals= true;
                }
            }
        }
        return isEquals;
    }
}
