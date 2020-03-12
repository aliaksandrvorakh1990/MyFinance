package by.vorakh.training.my_finance.dao.entity;

import java.io.Serializable;

import by.vorakh.training.my_finance.bean.UserRole;

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 96853345890041118L;
    
    private String login;
    private String password;
    private UserRole role;
    
    public UserEntity() {}

    public UserEntity(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
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
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return String.format("%s [login=%s, password=%s, role=%s]",
                getClass().getName(), login, password, role);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        UserEntity other = (UserEntity) obj;
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
        return true;
    }

}
