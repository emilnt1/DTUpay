package Endpoints;

import controller.UserController;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.Customer;
import org.acme.Merchant;

import javax.ws.rs.core.MediaType;

@Path("/merchants")
public class MerchantEndpoint {
    UserController userController = new UserController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMerchant(Merchant merchant)
    {
        userController.createMerchant(merchant);
    }

    @GET
    public void getMerchant(Merchant merchant){
    }



    

}
