package Endpoints;

import controller.UserController;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.Customer;

import javax.ws.rs.core.MediaType;

@Path("/users")
public class MerchantEndpoint {
    UserController userController = new UserController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(Customer customer)
    {
        userController.createCustomer(customer);
    }

    

}
