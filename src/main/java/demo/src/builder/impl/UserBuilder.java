package demo.src.builder.impl;

import demo.src.builder.Builder;
import demo.src.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String street = resultSet.getString("street");
        int age = resultSet.getInt("age");
        return new User(id, login, age, street);
    }
}
