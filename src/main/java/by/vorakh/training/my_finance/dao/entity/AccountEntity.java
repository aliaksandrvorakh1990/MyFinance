package by.vorakh.training.my_finance.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountEntity implements Serializable {

    private static final long serialVersionUID = -1421457198854331542L;
    
    private String id;
    private String name;
    private BigDecimal balance;
    
    public AccountEntity() {}

    public AccountEntity(String id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
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
    
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return String.format("%s [id=%s, name=%s, balance=%s]",
                getClass().getName(), id, name, balance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        AccountEntity other = (AccountEntity) obj;
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
        return true;
    }

}
