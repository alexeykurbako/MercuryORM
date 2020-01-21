package demo.src.DAO.factory;

import DAO.impl.UserDaoImpl;

import java.sql.Connection;


public class DaoFactory implements AutoCloseable {
    private Connection connection;

    public DaoFactory(){
        this.connection = ConnectionPool.getConnectionPool().getConnection();
    }

    public UserDaoImpl getUserDao(){
        return new UserDaoImpl(connection);
    }

    @Override
    public void close() {
        ConnectionPool.getConnectionPool().returnConnectionToPool(connection);
    }
}
