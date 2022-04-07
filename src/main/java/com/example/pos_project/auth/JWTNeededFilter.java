package com.example.pos_project.auth;



import com.example.pos_project.resource.LoginRescource;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.text.ParseException;

@JWTNeeded
@Priority(Priorities.AUTHORIZATION)
@Provider
public class JWTNeededFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String token = crc.getHeaderString("Authorization");

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            if(jwsObject.verify(new MACVerifier(LoginRescource.SECRET))){
                String payload = (String) crc.getProperty("payload");
                crc.setProperty("payload", payload);
            }
        } catch (Exception e) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            e.printStackTrace();
        }
    }
}
