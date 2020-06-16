package DAO.DAO;

import builder.Builder;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDAO<T> implements Dao<T> {
    protected Connection connection;

    protected abstract void prepareUpdateStatement(PreparedStatement preparedStatement, String... params) throws DaoException;

    protected abstract void prepareRemoveStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected abstract void prepareExecuteStatement(PreparedStatement preparedStatement, String... params) throws DaoException;

    protected abstract void prepareInsertStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected List<T> executeQuery(String executeQuery, Builder<T> builder, String... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(executeQuery);
            prepareExecuteStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected void executeUpdate(String updateQuery, String... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            prepareUpdateStatement(statement, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected void executeInsert(String insertQuery, T object) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            prepareInsertStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    protected void executeRemove(String removeQuery, T object) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(removeQuery);
            prepareRemoveStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

