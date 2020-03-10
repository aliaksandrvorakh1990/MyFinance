package by.vorakh.training.my_finance.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import by.vorakh.training.my_finance.bean.ExpenseType;

public class RecordEntity implements Serializable {

    private static final long serialVersionUID = 4879339749334570877L;
    
    private String id;
    private BigDecimal amount;
    private ExpenseType type;
    
    public RecordEntity() {}

    public RecordEntity(String id, BigDecimal amount, ExpenseType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s [id=%s, amount=%s, type=%s]", 
                getClass().getName(), id, amount, type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RecordEntity other = (RecordEntity) obj;
        if (amount == null) {
            if (other.amount != null) {
                return false;
            }
        } else if (amount.compareTo(other.amount) != 0) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (id.compareTo(other.id) != 0) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
    
}
