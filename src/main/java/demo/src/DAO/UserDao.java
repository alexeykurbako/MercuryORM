package demo.src.DAO;

import demo.src.entity.User;
import demo.src.exception.DaoException;
import entity.User;
import exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
