package Endpoints;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/pay")
public class AccountEndpoint {

    @POST
    @Path("/account")
    public String createAccount() {
        BankService bank = new BankServiceService().getBankServicePort();

        return null;
    }



}
