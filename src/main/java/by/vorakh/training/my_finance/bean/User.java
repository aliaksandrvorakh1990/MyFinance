package by.vorakh.training.my_finance.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = -5179173428408300964L;
    
    private String login;
    private String password;
    private UserRole role;
    private List<Account> accounts;
    
    public User() {}
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
   
        
    public User( String login, String password, UserRole role, 
             List<Account> accounts) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.accounts = accounts;
    }
    
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }
    
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return String.format("%s [login=%s, password=%s, role=%s, accounts=%s]",
                getClass().getName(), login, password, role, accounts);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
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
        User other = (User) obj;
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }   
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }  
        if (role != other.role) {
            return false;
        } 
        if (accounts == null) {
            if (other.accounts != null) {
                return false;
            }
        } else if (!equals(accounts ,other.accounts)) {
            return false;
        }
        return true;
    }
    
    private boolean equals(List<Account> listA, List<Account> listB) {
        boolean isEquals = false;
        if (listA!=null && listB!=null) {
            int sizeA = listA.size();
            int sizeB = listB.size();
            if (sizeA == sizeB) {
                Account[] arrA= listA.toArray(new Account[sizeA]);
                ArrayList<Account> copyA = new ArrayList<>(Arrays.asList(arrA));
                copyA.removeAll(listB);
                if (copyA.isEmpty()) {
                    isEquals= true;
                }
            }   
        }        
        return isEquals;
    }
}
