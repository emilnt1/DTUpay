package Endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/some")
public class SomeEndpoint {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "hello";
    }
}
