package DAO.DAO.impl;


import DAO.AbstractDAO;
import DAO.UserDao;
import builder.impl.UserBuilder;
import entity.User;
import exception.DaoException;
import exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl extends AbstractDAO<User> implements UserDao {

    private static final String GET_BY_LOGIN_AND_PASSWORD = "SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE login = ? and password = MD5(?) and is_locked = 'false'";

    private static final String GET_USER_BY_ID = "SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ?";

    private static final String GET_ALL = "SELECT user_id,login,email,password,money,role,is_locked FROM totalizator.user";

    private static final String REMOVE_BY_ID = "DELETE user_id,login,email,password,money,role,is_locked FROM totalizator.user WHERE user_id = ?";

    private static final String UPDATE_USER = "UPDATE totalizator.user SET login = ?, email = ?, password = ?, money = ?, role = ?, is_locked = ? WHERE user_id = ?";

    private static final String SAVE_USER = "INSERT INTO totalizator.user(user_id,login,email,password,money,role,is_locked) VALUES (?,?,?,?,?,?,?)";

    public UserDaoImpl(Connection connection) {
        super.connection = connection;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        List<User> list = executeQuery(GET_BY_LOGIN_AND_PASSWORD, new UserBuilder(), login, password);
        if(list.size () == 0){
            return Optional.empty();
        }else {
            return Optional.ofNullable(list.iterator().next());
        }
    }


    @Override
    public Optional<User> getById(long id) throws DaoException {
        List<User> list = executeQuery(GET_USER_BY_ID, new UserBuilder(), String.valueOf(id));
        if(list.size () == 0){
            return Optional.empty();
        }else {
            return Optional.ofNullable(list.iterator().next());
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> list = executeQuery(GET_ALL, new UserBuilder(), "", "");
        return list;
    }

    @Override
    public void save(User object) throws DaoException {
        executeInsert(SAVE_USER, object);
    }

    @Override
    public void removeById(User object) throws DaoException {
        executeRemove(REMOVE_BY_ID, object);
    }

    public void update(User object) throws ServiceException {
        String login = object.getLogin();
        String email = object.getEmail();
        String pass = object.getPassword();
        String money = String.valueOf(object.getMoney());
        String role = object.getRole().getValue();
        String isLocked = Boolean.toString(object.isLocked());
        String id = String.valueOf(object.getId());
        try {
            executeUpdate(UPDATE_USER, login, email, pass, money, role, isLocked, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, String... params) throws DaoException {
        try {
            for(int i = 0; i < params.length; i++){
                preparedStatement.setString (i + 1, params[i]);
            }
        } catch (SQLException e) {
            System.out.println("exception in preparedStatementUpdate in  implementation of UserDao class ");
            throw new DaoException (e);
        }
    }

    @Override
    protected void prepareRemoveStatement(PreparedStatement preparedStatement, User object) throws DaoException {
        try {
            preparedStatement.setLong (1, object.getId());
        } catch (SQLException e) {
            System.out.println("exception in preparedStatementUpdate in  implementation of UserDao class ");
            throw new DaoException (e);
        }
    }

    @Override
    protected void prepareExecuteStatement(PreparedStatement preparedStatement, String ...params) throws DaoException {
        try {
            for(int i = 0; i < params.length; i++){
                preparedStatement.setString (i + 1, params[i]);
            }
        } catch (SQLException e) {
            System.out.println("exception in preparedStatementExecute in  implementation of UserDao class ");
        }
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement preparedStatement, User object) throws DaoException {
        try {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString (2, object.getLogin());
            preparedStatement.setString (3, object.getEmail());
            preparedStatement.setString (4, object.getPassword());
            preparedStatement.setBigDecimal (5, object.getMoney());
            preparedStatement.setString (6, object.getRole().getValue());
            preparedStatement.setBoolean (7, object.isLocked());
        } catch (SQLException e) {
            System.out.println("exception in preparedStatementInserts in  implementation of UserDao class ");
        }
    }
}
