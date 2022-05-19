/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.blackJack.player.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@NoArgsConstructor
public class DBplayer {
    private  int id;
    private String usrname;
    private  String usrpwd;
    private int credit;

//    public boolean checkPassword(String pwd)
//    {
////        if(usrpwd == pwd.hashCode())
////        {
////            return true;
////        }
////        else
////        {
////            return false;
////        }
//    }

    public String makePassword(DBplayer player) {
        String password="";
        password+=player.getUsrpwd()+player.getUsrname();
        return password.hashCode()+"";
    }

    public DBplayer(int id, String usrname, String usrpwd, int credit) {
        this.id = id;
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        this.credit = credit;
    }

    public DBplayer(int id, String usrname, int credit) {
        this.id = id;
        this.usrname = usrname;
        this.credit = credit;
    }

    public DBplayer(/*int id, */String usrname, String usrpwd/*, int credit*/) {
//        this.id = id;
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        this.credit = credit;
    }

//    public DBplayer(String usrname, String usrpwd, int id, String usrpwd1) {
//        this.usrname = usrname;
//        this.id = id;
//        this.usrpwd = usrpwd1;
////        this.usrpwd = usrpwd.hashCode();
//        this.credit = 100;
//    }


    public static void main(String[] args) {
        String pwd = "adminadmin";
        pwd = pwd.hashCode()+"";
        System.out.println(pwd);
    }
}
