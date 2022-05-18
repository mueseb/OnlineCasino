package at.kaindorf.onlinecasino.db.connection;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class DB_Access {

    private static DB_Access instance;
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DB_Database database = null;
    private Connection connection;

    public DB_Access() throws ClassNotFoundException {
        this.database = new DB_Database();
    }

    public static DB_Access getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DB_Access();
        }
        return instance;
    }

    public void connect() throws SQLException {
        database.connect();
    }

    public void disconnect() throws SQLException {
        database.disconnect();
    }

    public DB_Database getDatabase() {
        return database;
    }

}
