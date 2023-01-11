package Services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/merchant")
public class MerchantResource {

    @POST
    @Path("/create")
    public String createMerchant() {
        return null;
    }

    @GET
    @Path("/get")
    public String getMerchant() {
        return null;
    }

}
