/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.db.database.DB_Access;
import at.kaindorf.onlinecasino.db.database.DB_CachedConnection;
import at.kaindorf.onlinecasino.db.database.DB_Database;
import at.kaindorf.onlinecasino.db.database.DB_PrepStat;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class BlackjackDB {

    public DB_Access db_access = null;
    public DB_Database db_database = null;
    private Connection connection;
    private DB_CachedConnection cache;
    private static BlackjackDB theInstance;

    private PreparedStatement getUserByIDStat;
    private PreparedStatement insertUser;
    private PreparedStatement updateUserById;
    private PreparedStatement checkUserPassword;
    private PreparedStatement getUserByName;
    private PreparedStatement checkUserDuplicate;
    private PreparedStatement updateUserBalance;
    private PreparedStatement getGamesByID;
    private PreparedStatement insertGameByID;

    private BlackjackDB() throws SQLException, ClassNotFoundException {
        db_access = new DB_Access();
        db_access.connect();
        db_database = db_access.getDatabase();
        connection = db_database.getConnection();
    }

    public static BlackjackDB getInstance() throws SQLException, ClassNotFoundException {
        if(theInstance == null)
        {
            theInstance = new BlackjackDB();
        }
        return theInstance;
    }


    //gets Username and balance by ID
    public DBplayer getUserByID(int id) throws SQLException {
        if (getUserByIDStat == null) {
            getUserByIDStat = db_database.getConnection().prepareStatement(DB_PrepStat.getUserByID.sqlValue);
        }
        getUserByIDStat.setInt(1,id);
        //cache.releaseStatement(getGamesByID); //TODO ?
        ResultSet rs = getUserByIDStat.executeQuery();
        rs.next();
        return new DBplayer(id,rs.getString("name"),rs.getInt("balance"));
    }

    //check if user exist by checking if names exists in database
    public boolean checkIfUserExists(String name) throws SQLException {
        if(checkUserDuplicate == null)
        {
            checkUserDuplicate = db_database.getConnection().prepareStatement(DB_PrepStat.checkUserDuplicate.sqlValue);
        }
        checkUserDuplicate.setString(1,name);
        ResultSet rs = checkUserDuplicate.executeQuery();
        rs.next();
        if(rs.getInt("count")==0)
        {
            return false;
        }
        return true;
    }

    public boolean checkUserPassword(String name, String pwd) throws SQLException {
//        if(!checkIfUserExists(name))
//        {
//            return false;
//        }
        if(checkUserPassword == null)
        {
            checkUserPassword = db_database.getConnection().prepareStatement(DB_PrepStat.checkUserPassword.sqlValue);
        }
        checkUserPassword.setString(1,name);
        ResultSet rs = checkUserPassword.executeQuery();
        rs.next();
        if(rs.getString("password").equals(pwd))
        {
            return true;
        }
        return false;
    }

    public boolean insertUser(DBplayer user) throws SQLException {
        if(insertUser == null)
        {
            insertUser = db_database.getConnection().prepareStatement(DB_PrepStat.insertUser.sqlValue);
        }
        if(checkIfUserExists(user.getUsrname()))
        {
            return false;
        }
        //insertUser.setInt(1,user.getId());
        insertUser.setString(1,user.getUsrname());
        insertUser.setString(2,user.getUsrpwd());
        //insertUser.setInt(4,user.getCredit());
        int rs = insertUser.executeUpdate();
//        if(rs!=1)
//        {
//            return false;
//        }
        return true;
    }

    public boolean updateBalance(int id,int balance) throws SQLException {
        if(updateUserBalance == null)
        {
            updateUserBalance = db_database.getConnection().prepareStatement(DB_PrepStat.updateUserBalance.sqlValue);
        }
        updateUserBalance.setInt(1,id);
        updateUserBalance.setInt(2,balance);
        int rs = updateUserBalance.executeUpdate();
        if(rs!=1)
        {
            return false;
        }
        return true;
    }

    public boolean insertGameStat(DBgame game) throws SQLException {
        if(insertGameByID == null)
        {
            insertGameByID = db_database.getConnection().prepareStatement(DB_PrepStat.insertGameByID.sqlValue);
        }
        insertGameByID.setInt(1,game.getId());
        return true;
    }

    public List<DBgame> getGamesByUserID(int id) throws SQLException {
        if(getGamesByID == null)
        {
            getGamesByID = db_database.getConnection().prepareStatement(DB_PrepStat.getGamesByID.sqlValue);
        }
        getGamesByID.setInt(1,id);
        ResultSet rs = getGamesByID.executeQuery();
        List<DBgame> gameList = new ArrayList<>();
        while(rs.next())
        {
            gameList.add(new DBgame()); //TODO parameters
        }
        return gameList;
    }





//        public void insertUser(DBplayer player) throws SQLException {
//        if (insertUser == null) {
//            insertUser = db_database.getConnection().prepareStatement(DB_PrepStat.insertUser.toString());
//        }
//        insertStudentStat.setInt(1, student.getClassid());
//        insertStudentStat.setInt(2, student.getCatno());
//        insertStudentStat.setString(3, student.getFirstname());
//        insertStudentStat.setString(4, student.getLastname());
//        insertStudentStat.setString(5, (student.getGender() + ""));
//        insertStudentStat.setDate(6, Date.valueOf(student.getDateofbirth()));
//        insertStudentStat.executeUpdate();
//    }

//    public boolean insertPlayer(DBplayer player) throws SQLException {
//        if(!checkForDuplicate(player.getUsrname()))
//        {
//            String sqlString = "INSERT INTO player (name, password) VALUES ("+player.getUsrname()+player.getUsrpwd()+");";
//            Statement statement = db_database.getStatement();
//            statement.executeQuery(sqlString);
//            return true;
//        }
//        return false;
//    }

    public static void main(String[] args) {
        try {
            BlackjackDB blackjackDB = getInstance();
            System.out.println("Connected?");
            //System.out.println(blackjackDB.getPlayerByID(1));
//            blackjackDB.getUserByID2(1);
            Connection connection = blackjackDB.getConnection();
            DBplayer userByID = blackjackDB.getUserByID(0);
            System.out.println(userByID.getUsrname());
            connection.close();
            System.out.println("Closed Connection");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
