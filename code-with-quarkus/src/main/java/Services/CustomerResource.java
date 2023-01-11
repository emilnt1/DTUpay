package Services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/customer")
public class CustomerResource {

    @POST
    @Path("/create")
    public String createCustomer() {
        return null;
    }

    @GET
    @Path("/get")
    public String getCustomer() {
        return null;
    }

}
