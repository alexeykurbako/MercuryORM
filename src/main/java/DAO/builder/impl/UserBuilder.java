package DAO.builder.impl;

import builder.Builder;
import entity.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt("user_id");
        String login = resultSet.getString("login");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        BigDecimal money = resultSet.getBigDecimal("money");
        Role role = Role.valueOf(resultSet.getString("role"));
        boolean isLocked = Boolean.valueOf(resultSet.getString("is_locked"));
        return new User(id, login, email, password, money, role, isLocked);
    }
}
