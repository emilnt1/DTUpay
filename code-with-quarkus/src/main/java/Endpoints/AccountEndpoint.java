package Endpoints;

import controller.BankController;
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
    BankController bankController = new BankController();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean verifyAccount(String id) {
        return bankController.verifyAccount(id);
    }


}
