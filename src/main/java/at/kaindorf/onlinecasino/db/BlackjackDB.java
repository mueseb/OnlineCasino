/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.db.connection.DB_Access;
import at.kaindorf.onlinecasino.db.connection.DB_CachedConnection;
import at.kaindorf.onlinecasino.db.connection.DB_Database;
import at.kaindorf.onlinecasino.db.connection.DB_PrepStat;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class BlackjackDB {

    public String salt = "d8gw6b4ghlakg45w8";

    public DB_Access db_access;
    public DB_Database db_database;
    private Connection connection;
    private DB_CachedConnection cache;
    private static BlackjackDB theInstance;

    private PreparedStatement getUserByIDStat;
    private PreparedStatement getGamesByID;
    private PreparedStatement getUserByName;
    private PreparedStatement getUserBalance;

    private PreparedStatement insertUser;
    private PreparedStatement saveGame;

    private PreparedStatement updateUserById;
    private PreparedStatement updateUserBalance;

    private PreparedStatement checkUserPassword;
    private PreparedStatement checkUserDuplicate;


    private BlackjackDB() throws SQLException, ClassNotFoundException {
        db_access = new DB_Access();
        db_access.connect();
        db_database = db_access.getDatabase();
        connection = db_database.getConnection();
    }

    public int getUserIdByName(String name) throws SQLException {
        if(getUserByName == null)
        {
            getUserByName = connection.prepareStatement(DB_PrepStat.getUserByName.sqlValue);
        }
        getUserByName.setString(1,name);
        ResultSet rs = getUserByName.executeQuery();
        rs.next();
        return rs.getInt("playerid");
    }

    public static BlackjackDB getInstance() throws SQLException, ClassNotFoundException {
        if(theInstance == null)
        {
            theInstance = new BlackjackDB();
        }
        return theInstance;
    }

    public int getUserBalance(String name) throws SQLException {
        if (getUserBalance == null) {
            getUserBalance = connection.prepareStatement(DB_PrepStat.getUserBalance.sqlValue);
        }
        getUserBalance.setString(1,name);

        //cache.releaseStatement(getGamesByID); //TODO ?
        ResultSet rs = getUserBalance.executeQuery();
        rs.next();
        return rs.getInt("balance");
    }

    //gets username and balance by ID
    public DBplayer getUserDataByID(int id) throws SQLException {
        if (getUserByIDStat == null) {
            getUserByIDStat = connection.prepareStatement(DB_PrepStat.getUserByID.sqlValue);
        }
        getUserByIDStat.setInt(1,id);
        //cache.releaseStatement(getGamesByID); //TODO ?
        ResultSet rs = getUserByIDStat.executeQuery();
        rs.next();
        return new DBplayer(id,rs.getString("name"),rs.getInt("balance"));
    }

    //check if player exists by name | Returns false if no player exists
    public boolean checkIfUserExists(String name) throws SQLException {
        if(checkUserDuplicate == null)
        {
            checkUserDuplicate = connection.prepareStatement(DB_PrepStat.checkUserDuplicate.sqlValue);
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

    //Gets and compares player password by name | Returns false if passwords are different
    public boolean checkUserPassword(String name, String pwd) throws SQLException {
//        if(!checkIfUserExists(name))
//        {
//            return false;
//        }
        if(checkUserPassword == null)
        {
            checkUserPassword = connection.prepareStatement(DB_PrepStat.checkUserPassword.sqlValue);
        }
        checkUserPassword.setString(1,name);
        ResultSet rs = checkUserPassword.executeQuery();
        rs.next();
        Hash hash = new Hash();
        return hash.checkHashedPassword(rs.getString("password"), pwd, salt);
    }

    //Inserts player into Database | Returns false if player already exists/failed to insert
    public boolean insertUser(DBplayer user) throws SQLException {
        if(insertUser == null)
        {
            insertUser = connection.prepareStatement(DB_PrepStat.insertUser.sqlValue);
        }
        if(checkIfUserExists(user.getUsrname()))
        {
            return false;
        }
        Hash hash = new Hash();
        String password =hash.getHashedPassword(user.getUsrpwd(),salt);
        insertUser.setString(1,user.getUsrname());
        insertUser.setString(2,password);
        int rs = insertUser.executeUpdate();
        return rs == 1;
    }

    //Updates player Balance | Returns false if no user has been found/updated
    public boolean updateBalance(int id,int balance) throws SQLException {
        if(updateUserBalance == null)
        {
            updateUserBalance = connection.prepareStatement(DB_PrepStat.updateUserBalance.sqlValue);
        }
        updateUserBalance.setInt(1,id);
        updateUserBalance.setInt(2,balance);
        int rs = updateUserBalance.executeUpdate();
        return rs == 1;
    }

    //TODO finishing
    //inserts finished game with playerID
    public boolean saveGameStat(DBgame game) throws SQLException {
        if(saveGame == null)
        {
            saveGame = connection.prepareStatement(DB_PrepStat.saveGame.sqlValue);
        }
        //1:Player Win; 2:Dealer Win; 3:Draw
        int newBalance = getUserBalance(getUserDataByID(game.getPlayerID()).getUsrname());
        switch(game.getResult())
        {
            case 1:
                newBalance = newBalance + game.getBet() * 2;
                break;
            case 2:
                newBalance = newBalance - game.getBet();
                break;
        }
        updateBalance(game.getPlayerID(),newBalance);
        saveGame.setInt(1,game.getPlayerID());
        saveGame.setInt(2,game.getBet());
        saveGame.setString(3,game.getDealerHand().getCards().toString());
        saveGame.setString(4,game.getPlayerHand().getCards().toString());
        saveGame.setTimestamp(5,game.getStartTime());
        saveGame.setTimestamp(6,game.getEndTime());
        saveGame.setInt(7,game.getResult());
        return true;
    }

    //TODO finishing
    //Gets game List from player by playerID
    public List<DBgame> getGamesByUserID(int id) throws SQLException {
        if(getGamesByID == null)
        {
            getGamesByID = connection.prepareStatement(DB_PrepStat.getGamesByID.sqlValue);
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

//    public static void main(String[] args) {
//        try {
//            BlackjackDB blackjackDB = getInstance();
//            System.out.println("Connected?");
//            //System.out.println(blackjackDB.getPlayerByID(1));
////            blackjackDB.getUserByID2(1);
////            Connection connection = blackjackDB.getConnection();
////            DBplayer userByID = blackjackDB.getUserDataByID(0);
//            System.out.println(blackjackDB.getUserBalance("admin"));
////            connection.close();
//            System.out.println("Closed Connection");
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}