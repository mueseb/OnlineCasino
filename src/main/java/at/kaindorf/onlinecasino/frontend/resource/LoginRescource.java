package at.kaindorf.onlinecasino.frontend.resource;

import at.kaindorf.onlinecasino.db.database.DB_Access;
import at.kaindorf.onlinecasino.frontend.beans.LoginData;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/login")
public class LoginRescource {
    public static final String SECRET = "_this_secret_is_not_long_enought_";
    public DB_Access db;

    public void connectDB() throws SQLException {
        db.connect();

    }


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
    public Response login(LoginData loginData) {

        if (loginData.getName().equals("admin") && loginData.getPwd().equals("admin")) {
            return Response.ok().header("Authorization", creatJWT(loginData.getName())).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
