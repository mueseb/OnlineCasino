/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.db.database.DB_Access;
import at.kaindorf.onlinecasino.db.database.DB_Database;
import at.kaindorf.onlinecasino.db.database.DB_PrepStat;

import java.sql.*;

public class BlackjackDB {

    public DB_Access db_access;
    public DB_Database db_database;

    private PreparedStatement getUserByID;
    private PreparedStatement insertUser;
    private PreparedStatement updateUserById;

    public BlackjackDB() throws SQLException {
        db_access.connect();
        db_database = db_access.getDatabase();
    }

    public DBplayer getPlayerByID(int id) throws SQLException {
        String sqlString = "SELECT * FROM player WHERE playerid=id;";
//        if (getUserByID == null) {
//            getUserByID = db_database.getConnection().prepareStatement(DB_PrepStat.getUserByID.toString());
//        }
        Statement statement = db_database.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        DBplayer player = new DBplayer(rs.getInt("playerid"),rs.getString("name"),rs.getString("password"),rs.getInt("balance"));
        return player;
    }

    public boolean insertPlayer(DBplayer player) throws SQLException {
        if(!checkForDuplicate(player.getUsrname()))
        {
            String sqlString = "INSERT INTO player (name, password) VALUES ("+player.getUsrname()+player.getUsrpwd()+");";
            Statement statement = db_database.getStatement();
            statement.executeQuery(sqlString);
            return true;
        }
        return false;
    }

    public boolean checkForDuplicate(String name) throws SQLException {
        String sqlString = "SELECT * FROM player WHERE name="+name+";";
        Statement statement = db_database.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        return rs.next();
    }

    public void updatePlayer(DBplayer player) throws SQLException {
        String sqlString = "UPDATE player SET balance="+player.getCredit()+" WHERE playerID="+player.getId()+";";
        Statement statement = db_database.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
    }
}
