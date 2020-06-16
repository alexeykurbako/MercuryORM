package DAO.DAO;

import exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(long id) throws DaoException;
    List<T> getAll() throws DaoException;
    void save(T object) throws DaoException;
    void removeById(T object) throws DaoException;
}
