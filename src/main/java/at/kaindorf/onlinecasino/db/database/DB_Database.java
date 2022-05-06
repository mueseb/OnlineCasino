package at.kaindorf.onlinecasino.db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Database {
    private final String DB_URL;
    private final String DB_DRIVER;
    private final String DB_USER;
    private final String DB_PASSWORD;

    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException {
        DB_DRIVER = DB_Properties.getProperty("db_driver");
        DB_URL = DB_Properties.getProperty("db_url");
        DB_USER = DB_Properties.getProperty("db_user");
        DB_PASSWORD = DB_Properties.getProperty("db_password");
        Class.forName(DB_DRIVER);
    }

    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }

        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        cachedConnection = new DB_CachedConnection(connection);
    }

    public void disconnect() throws SQLException {

        if (connection != null) {
            connection.close();
            cachedConnection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection != null || cachedConnection != null) {
            throw new IllegalStateException("not connected to database");
        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection != null || cachedConnection != null) {
            throw new IllegalStateException("not connected to database");
        }
        cachedConnection.releaseStatement(statement);

    }
}
