package DAO.service;

import DAO.factory.DaoFactory;
import DAO.impl.UserDaoImpl;
import entity.User;
import exception.DaoException;
import exception.ServiceException;
import java.util.List;
import java.util.Optional;


public class UserService {
    public void update(User user) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDaoImpl userDao = factory.getUserDao();
            userDao.update(user);
        }
    }

    public Optional<User> getById(long id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDaoImpl userDao = factory.getUserDao();
            return userDao.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Failed login",e);
        }
    }


    public List<User> getAll() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDaoImpl userDao = factory.getUserDao();
            return userDao.getAll();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Failed login",e);
        }
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        Optional<User> actual = null;
        try (DaoFactory factory = new DaoFactory()) {
            UserDaoImpl dao = factory.getUserDao();
            actual = dao.findUserByLoginAndPassword(username, password);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Failed login",e);
        }
        return actual == null ? Optional.empty() : actual;
    }
}
