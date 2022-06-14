/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 20.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.db.DBdata;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DBplayer {
    private  int id;
    private String usrname;
    private  String usrpwd;
    private int credit;

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
}
