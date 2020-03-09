package by.vorakh.training.my_finance.service.entity;

import java.io.Serializable;

import by.vorakh.training.my_finance.bean.UserRole;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private UserRole role;

    public LoginResponse() {}

    public LoginResponse(Integer id, UserRole role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {

    return String.format("%s [id=%s, role=%s]", getClass().getName(),
        id, role);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        LoginResponse other = (LoginResponse) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (id.compareTo(other.id) != 0) {
            return false;
        }
        if (role != other.role) {
            return false;
        }
        return true;
    }

}
