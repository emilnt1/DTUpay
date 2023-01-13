package Endpoints;

import controller.BankController;
import controller.UserController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.quarkus.logging.Log;
import org.acme.Customer;
import org.jboss.logging.Logger;

@Path("/customers")
public class CustomerEndpoint {
    UserController userController = new UserController();
    BankController bankController = new BankController();

    private static final Logger LOG = Logger.getLogger(CustomerEndpoint.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void createCustomer(Customer customer)
    {
        LOG.info("Recieved this name on user: " + customer.getFirstName());
        LOG.info("Recieved ID: " + customer.getAccountId());

        if (bankController.verifyAccount(customer.getAccountId())) {
            userController.createCustomer(customer);
        } else
        {
            throw new IllegalArgumentException("Account does not exist");
        }

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getService(){
        return "Hello from REST";
    }



}
