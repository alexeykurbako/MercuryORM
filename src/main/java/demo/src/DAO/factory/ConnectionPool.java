package demo.src.DAO.factory;

import demo.src.exception.ConnectionException;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool implements Closeable {
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/totalizator?serverTimezone=Europe/Minsk&autoReconnect=true&useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "admin";
    private static final ConnectionPool POOL = new ConnectionPool();
    private final static int poolSize = 2;

    private ArrayBlockingQueue<Connection> connections;
    private ReentrantLock lockForReturnConnection;

    public static ConnectionPool getConnectionPool(){
        return POOL;
    }

    private ConnectionPool()  {
        connections = new ArrayBlockingQueue<>(poolSize);
        lockForReturnConnection = new ReentrantLock();

        for(int i = 0; i < poolSize; i++){
            Connection connection;
            try  {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connections.offer(connection);
            }
            catch (SQLException exc){
                System.out.println(exc);
                System.out.println("Error in connection pool");
            }
        }
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = connections.take();
        }
        catch(InterruptedException exc){
            System.out.println("Error in connection pool in getConnection method");
            throw new ConnectionException(exc);
        }
        return connection;
    }

    public void returnConnectionToPool(Connection connection){
        lockForReturnConnection.lock();
        if(!connections.contains(connection)) {
            connections.offer(connection);
        }
        lockForReturnConnection.unlock();
    }

    @Override
    public void close() throws IOException {
        for(Connection connection : connections){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error in connection pool in closeAll method");
                throw new ConnectionException(e);
            }
        }
    }
}