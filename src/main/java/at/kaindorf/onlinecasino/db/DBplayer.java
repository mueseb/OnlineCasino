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
import java.util.Optional;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class DBplayer {
    private int id;
    private String usrname;
    private String usrpwd;
    private int credit;

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

    public DBplayer(String usrname, String usrpwd/*, int credit*/) {
//        this.id = id[0];
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        this.credit = credit;
    }


//    public static void main(String[] args) {
//        String usrname = "admin";
//        String pwd = "admin";
//
//        String hash = usrname+pwd;
//        hash = hash.hashCode()+"";
//        System.out.println(hash);
//    }
}
