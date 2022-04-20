/*
    Klasse:  4BHIF 
    @author: Sebastian Münzer
*/
package at.kaindorf.onlinecasino.db;

import at.kaindorf.onlinecasino.frontend.beans.LoginData;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveUser {
    private URL resource = getClass().getResource("players.txt");
    private String file = resource.getFile();

    public void writeToFile(LoginData usr) throws IOException {
        URL resource = getClass().getResource("players.txt");
        String file = resource.getFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        fw.append(usr.toString());
    }

    public List<LoginData> readFile() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<LoginData> loginDataList = new ArrayList<>();
        String line = "";
        while(br.readLine() != null) {
            line = br.readLine();
            String token[] = line.split(";");
            loginDataList.add(new LoginData(token[0],token[1]));
        }
        return loginDataList;
    }

}
