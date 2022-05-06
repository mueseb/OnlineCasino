package at.kaindorf.web.bl;

import at.kaindorf.web.beans.LoginData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class WebDBAccess {
    private List<LoginData> dumyDBList = new ArrayList<>();
    public static WebDBAccess instance;

    public WebDBAccess() {
        dumyDBList = new ArrayList<>();
        dumyDBList.add(new LoginData(0, "admin", "admin"));
    }

    public static WebDBAccess getInstance() {
        if (instance == null)
            instance = new WebDBAccess();
        return instance;
    }

    public void addPlayer(LoginData loginData){
        //todo: add player to real database

        int help = dumyDBList.get(dumyDBList.size() - 1).getId();
        loginData.setId(help);
        dumyDBList.add(loginData);

    }

    public boolean checkNewPlayer(LoginData loginData){
        for (int i = 0; i < dumyDBList.size(); i++) {
            if(dumyDBList.get(i).getUsername().equals(loginData.getUsername())){
                return false;
            }
        }
        addPlayer(loginData);
        return true;
    }

    public boolean checkPlayerDB(LoginData loginData){
        for (int i = 0; i < dumyDBList.size(); i++) {
            if(dumyDBList.get(i).getUsername().equals(loginData.getUsername())){
                return true;
            }
        }
        return false;
    }

    public LoginData getPlayerByID(int id){
        return dumyDBList.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public LoginData getPlayerByUsername(String name){
        return dumyDBList.stream().filter(p -> p.getUsername() == name).findFirst().get();
    }

}
