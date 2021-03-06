/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 07.04.2022
 * @project-name: Online Casino
 */
package at.kaindorf.web.resource;

import at.kaindorf.onlinecasino.db.BlackjackDB;
import at.kaindorf.onlinecasino.db.DBdata.DBplayer;
import at.kaindorf.web.beans.LoginData;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/login")
public class LoginRescource {
    public static final String SECRET = "_this_secret_is_not_long_enough_";
    public BlackjackDB blackJackDB;

    public void setDatabase() throws SQLException, ClassNotFoundException {
        if(blackJackDB == null){
            blackJackDB = BlackjackDB.getInstance();
        }
    }

    public String creatJWT(String name) {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(name));
        try {
            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        } catch (JOSEException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    //checks for other chars in username
    public boolean checkUserForIllegalChars(LoginData userData)
    {
        return !userData.getUsername().matches("^[a-zA-Z0-9]*$");
    }

    @POST
    @Produces
    public Response login(LoginData loginData) {
        DBplayer player;
        player = new DBplayer(loginData.getUsername(),loginData.getPwd());
        System.out.println(loginData.getUsername());
        if(checkUserForIllegalChars(loginData))
        {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); //only allows letters and numbers in the username (prevents all sql injections)
        }
        try {
            BlackjackDB blackJackDB = BlackjackDB.getInstance();
            DBplayer userByID = blackJackDB.getUserDataByID(0);
            System.out.print(userByID.getUsrname());
            if (blackJackDB.checkIfUserExists(player.getUsrname())) {
                if(blackJackDB.checkUserPassword(player.getUsrname(),player.getUsrpwd()))
                {
                    return Response.ok().header("Authorization", creatJWT(loginData.getUsername())).build();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/createUser")
    public Response creatUser(LoginData UserData) {
        DBplayer player;
        player = new DBplayer(UserData.getUsername(),UserData.getPwd());
        if(checkUserForIllegalChars(UserData))
        {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); //only allows letters and numbers in the username (prevents all sql injections)
        }
        try {
            BlackjackDB blackJackDB = BlackjackDB.getInstance();
            if (blackJackDB.insertUser(player)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
