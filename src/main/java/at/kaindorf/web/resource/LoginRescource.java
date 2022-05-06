package at.kaindorf.web.resource;

import at.kaindorf.web.beans.LoginData;
import at.kaindorf.web.bl.WebDBAccess;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginRescource {
    public static final String SECRET = "_this_secret_is_not_long_enough_";
    public WebDBAccess instance = WebDBAccess.getInstance();

    public String creatJWT(String name) {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(name));

        try {
            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Produces
    public Response login(LoginData loginData) {
        loginData.setPwd("" + hashPassword(loginData));
        if(instance.checkPlayerDB(loginData))
            return Response.ok().header("Authorization", creatJWT(loginData.getUsername())).build();
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

//    @POST
//    @Produces
//    public Response creatUser(LoginData loginData){
//        //loginData = username, password
//        loginData.setPwd("" + hashPassword(loginData));
//        if(!instance.checkNewPlayer(loginData)){
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//        instance.addPlayer(loginData);
//        return Response.ok().build();
//    }

    //Hashes the password
    private int hashPassword(LoginData loginData){
        String preHash = "" + loginData.getUsername() + loginData.getPwd();
        return preHash.hashCode();
    }

}
