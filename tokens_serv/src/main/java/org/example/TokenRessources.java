package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tokens")
public class TokenRessources {


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String generateToken(){
        return "token";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "hello";
    }

}
