package at.kaindorf.onlinecasino.db.database;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Database {
    private  String DB_URL;
    private  String DB_DRIVER;
    private String DB_USER;
    private  String DB_PASSWORD;

    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException {
//        DB_DRIVER = DB_Properties.getProperty("db_driver");
//        DB_URL = DB_Properties.getProperty("db_url");
//        DB_USER = DB_Properties.getProperty("db_user");
//        DB_PASSWORD = DB_Properties.getProperty("db_password");
        initURL();
        System.out.println("DB_Database");
//        try {
//            DriverManager.registerDriver();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Class.forName(DB_DRIVER);
    }

    public void initURL()
    {
        DB_DRIVER = "org.postgresql.Driver";
        DB_URL = "jdbc:postgresql://107.20.254.204:5432/CasinoDB";
        DB_USER = "postgres";
        DB_PASSWORD = "2002";
    }

    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        initURL();
        System.out.println("connect");
        System.out.println(DB_URL+ DB_USER+ DB_PASSWORD);
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
//        if (connection != null || cachedConnection != null) {
//            throw new IllegalStateException("not connected to database");
//        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection != null || cachedConnection != null) {
            throw new IllegalStateException("not connected to database");
        }
        cachedConnection.releaseStatement(statement);

    }
}
