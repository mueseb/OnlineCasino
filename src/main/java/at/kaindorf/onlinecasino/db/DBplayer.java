/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import lombok.Data;

@Data
public class DBplayer {
    private  int id;
    private final String usrname;
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

}
