package at.kaindorf.onlinecasino.db.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class DB_CachedConnection {
    private Queue<Statement> statementQueue = new LinkedList<>();
    private Connection connection;

    public DB_CachedConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("not connected to database");
        }
        if (statementQueue.size() > 0) {
            return statementQueue.poll();
        }
        return connection.createStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection == null) {
            throw new IllegalStateException("not connected to database");
        }
        statementQueue.offer(statement);
    }


}
