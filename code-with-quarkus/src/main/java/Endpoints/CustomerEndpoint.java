package Endpoints;

import Exceptions.IllegalArgumentException;
import controller.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.*;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/customers")
public class CustomerEndpoint {
    UserService userService = new UserServiceFactory().getService();

    CustomerService customerService = new CustomerServiceFactory().getService();
    BankController bankController = new BankController();
    ReportController reportController = new ReportController();
    private static final Logger LOG = Logger.getLogger(CustomerEndpoint.class);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO getCustomer(@PathParam("id") String id) {
        return convertCustomerDTO(userService.getCustomer(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer createCustomer(CustomerDTO customerDTO)
    {
        Customer customer = this.convertCustomerDTO(customerDTO);

        LOG.info("Recieved this name on user: " + customer.getFirstName());
        LOG.info("Recieved ID: " + customerDTO.getAccount());

        if (bankController.verifyAccount(customer.getAccountId())) {
            return customerService.register(customer);
        } else
        {
            throw new IllegalArgumentException("Account does not exist");
        }

    }

    @GET
    @Path("/tokens/{id}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getTokens(@PathParam("id") String id, @PathParam("amount") int amount){

        return userService.issueTokens(amount, id);
    }

    @GET
    @Path("payments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerPaymentDTO> getCustomerReport(@PathParam("id") String id){
        return reportController.getCustomerReport(id);
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

    @DELETE
    @Path("{/id}")
    public void deleteCustomer(@PathParam("id") String id) {
        userService.deleteUser(id);
    }


}
