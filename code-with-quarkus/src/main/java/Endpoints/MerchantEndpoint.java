package Endpoints;

import controller.UserController;
import jakarta.batch.api.BatchProperty;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.Customer;
import org.acme.Merchant;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/merchants")
public class MerchantEndpoint {
    UserController userController = new UserController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createMerchant(Merchant merchant)
    {
        return userController.createMerchant(merchant);
    }

    public void getMerchant(Merchant merchant){
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getService(){
        return "Hello from REST";
    }






}
