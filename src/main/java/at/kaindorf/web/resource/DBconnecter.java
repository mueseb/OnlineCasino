/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.web.resource;

import at.kaindorf.onlinecasino.db.BlackjackDB;
import at.kaindorf.onlinecasino.db.DBplayer;

import java.sql.Connection;
import java.sql.SQLException;

public class DBconnecter {
    public static void main(String[] args) {
        try {
            BlackjackDB blackjackDB = BlackjackDB.getInstance();
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

    public static String getuserByID(int id) throws SQLException, ClassNotFoundException {
        BlackjackDB blackjackDB = BlackjackDB.getInstance();
        System.out.println("Connected?");
        //System.out.println(blackjackDB.getPlayerByID(1));
//            blackjackDB.getUserByID2(1);
        Connection connection = blackjackDB.getConnection();
        DBplayer userByID = blackjackDB.getUserByID(id);
        return userByID.getUsrname();
//        System.out.println(userByID.getUsrname());
//        connection.close();
//        System.out.println("Closed Connection");
    }
}
