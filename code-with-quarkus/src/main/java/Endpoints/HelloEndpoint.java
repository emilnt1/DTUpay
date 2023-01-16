package Endpoints;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloEndpoint {

    private static final Logger LOG = Logger.getLogger(HelloEndpoint.class);
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloService() {

        return "Hello from REST";
    }
}
