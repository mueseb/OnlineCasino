/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

public class
DBplayer {
    private final String usrname;
    private final int usrpwd;
    private int credit;

    public boolean checkPassword(String pwd)
    {
        if(usrpwd == pwd.hashCode())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public DBplayer(String usrname, int usrpwd, int credit) {
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        this.credit = credit;
    }

    public DBplayer(String usrname, String usrpwd) {
        this.usrname = usrname;
        this.usrpwd = usrpwd.hashCode();
        this.credit = 100;
    }

    public String getUsrname() {
        return usrname;
    }

    public int getUsrpwd() {
        return usrpwd;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
