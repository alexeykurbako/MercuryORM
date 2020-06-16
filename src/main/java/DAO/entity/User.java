package DAO.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User implements Entity {
    private long id;
    private String login;
    private String email;
    private String password;
    private BigDecimal money;
    private Role role;
    private boolean isLocked;
    public User(){}

    public User(long id, String login, String email, String password, BigDecimal money, Role role, boolean isLocked) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.money = money;
        this.role = role;
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isLocked == user.isLocked &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(money, user.money) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, money, role, isLocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", role=" + role +
                ", isLocked=" + isLocked +
                '}';
    }

    @Override
    public long getId() {
        return id;
    }
}
