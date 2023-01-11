package Endpoints;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/accounts")
public class AccountEndpoint {

    @POST
    public String createAccount() {


        return null;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloService() {
        return "Hello from REST";
    }
}
