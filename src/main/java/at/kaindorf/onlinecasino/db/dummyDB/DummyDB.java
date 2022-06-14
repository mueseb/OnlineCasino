/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

package at.kaindorf.onlinecasino.db.dummyDB;

import at.kaindorf.onlinecasino.db.DBdata.DBplayer;

import java.util.ArrayList;
import java.util.List;

//DummyDB for testing
public class DummyDB {
    List<DBplayer> users = new ArrayList<>();

    public DummyDB() {
        users.add(new DBplayer(1,"admin","admin",100));
    }

    public void addUser(String username, String pwd)
    {
        int i = users.get(users.size()-1).getId() + 1;
        DBplayer player = new DBplayer(i,username,pwd,100);
        users.add(player);
    }

    public boolean userexists(String name)
    {
        for (DBplayer user:users) {
            if(user.getUsrname().equals(name))
            {
                return true;
            }
        }
        return false;
    }
}
