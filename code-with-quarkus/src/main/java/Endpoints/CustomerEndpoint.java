package Endpoints;

import controller.BankController;
import controller.UserController;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.Customer;

import javax.ws.rs.core.MediaType;

@Path("/customers")
public class CustomerEndpoint {
    UserController userController = new UserController();
    BankController bankController = new BankController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(Customer customer)
    {
        if (bankController.verifyAccount(customer.getAccountId())) {
            userController.createCustomer(customer);
        } else
        {
            throw new IllegalArgumentException("Account does not exist");
        }


    }



}
