package Endpoints;

import controller.BankController;
import controller.PaymentController;
import controller.TokenController;
import controller.UserController;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.quarkus.logging.Log;
import org.acme.*;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/customers")
public class CustomerEndpoint {
    UserController userController = new UserController();
    BankController bankController = new BankController();
    TokenController tokenController = new TokenController();
    PaymentController paymentController = new PaymentController();
    private static final Logger LOG = Logger.getLogger(CustomerEndpoint.class);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO getCustomer(@PathParam("id") String id) {
        return convertCustomerDTO(userController.getCustomer(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createCustomer(CustomerDTO customerDTO)
    {
        Customer customer = this.convertCustomerDTO(customerDTO);

        LOG.info("Recieved this name on user: " + customer.getFirstName());
        LOG.info("Recieved ID: " + customerDTO.getAccount());

        if (bankController.verifyAccount(customer.getAccountId())) {
            return userController.createCustomer(customer);
        } else
        {
            throw new IllegalArgumentException("Account does not exist");
        }

    }

    @GET
    @Path("/tokens/{id}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getTokens(@PathParam("id") String id, @PathParam("amount") int amount){
        //TokenDTO tokenDTO = new TokenDTO();
        //tokenDTO.setTokens(tokenController.generateToken(amount, id));
        //return tokenDTO;
        return tokenController.generateToken(amount, id);
    }

    @GET
    @Path("payments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerPaymentDTO> getCustomerReport(@PathParam("id") String id){
        return paymentController.getCustomerReport(id);
    }




    private Customer convertCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setId(customerDTO.getId());
        customer.setTokens(customerDTO.getTokens());
        customer.setCpr(customerDTO.getCpr());
        customer.setAccountId(customerDTO.getAccount());

        return customer;
    }

    private CustomerDTO convertCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setCpr(customer.getCpr());
        customerDTO.setId(customer.getId());
        customerDTO.setAccount(customer.getAccountId());
        customerDTO.setTokens(customer.getTokens());

        return customerDTO;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getService(){
        return "Hello from REST";
    }


}
