package by.epam.lab.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 *  Designed to create, work and store database connections in the singleton style.
 */
public class ConnectionPool {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private Deque<Connection> connections;
    private Semaphore semaphore;

    private String url;
    private String user;
    private String password;
    private String driver;
    private int poolSize;

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    private ConnectionPool() {
        initializeData();
        createConnections();
    }

    /**
     * Designed for database data initialization.
     */
    private void initializeData() {
        connections = new ArrayDeque<>();

        try {
            Class<? extends ConnectionPool> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("database2.properties");

            Properties property = new Properties();
            property.load(inputStream);

            url = property.getProperty("db.url");
            user = property.getProperty("db.user");
            password = property.getProperty("db.password");
            driver = property.getProperty("db.driver");

            String poolSizeString = property.getProperty("db.poolSize");
            poolSize = Integer.parseInt(poolSizeString);

           Class.forName(driver);

        } catch (IOException e) {
            throw new IllegalArgumentException("File with properties not found! " + e.getMessage(), e);
            
        } catch (ClassNotFoundException e) { throw new
			  IllegalArgumentException("Driver is not found! " + e.getMessage(), e);
			 
        }

        semaphore = new Semaphore(poolSize);
    }

    /**
     * Designed for thread-safe retrieve database connection from storage.
     *
     * @return a {@link Connection} object that provide connection to database
     */
    public Connection getConnection() {

        try {
            lock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        } finally {
            lock.unlock();
        }
    }

    /**
     *  Creates connections and put them into storage
     */
    private void createConnections() {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.push(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException("Connections are not created!");
        }
    }

    /**
     *  Returns conection into the storage.
     *
     * @param connection - connection that should bu returned.
     */
    public void returnConnection(Connection connection) {
        try {
            lock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            lock.unlock();
        }
    }

}
