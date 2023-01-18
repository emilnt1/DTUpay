package service;

import dtu.ws.fastmoney.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Customer;
import org.acme.Merchant;
import org.acme.MerchantPayment;
import org.acme.Payment;

import java.util.LinkedList;
import java.util.List;

public class MerchantAPI {

    WebTarget baseUrl;

    public MerchantAPI() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8080/");
    }

    /*
    public boolean verifyBankAccount(String id) {
        return baseUrl.path("accounts").request().get(boolean.class);
    }
    */

    public String createAccount(User user, String accountId){
        Merchant merchant = new Merchant();
        merchant.setFirstName(user.getFirstName());
        merchant.setLastName(user.getLastName());
        merchant.setAccount(accountId);
        merchant.setCpr(user.getCprNumber());
        merchant.setId("-1");

        return setMerchant(merchant, MediaType.APPLICATION_JSON);
    }

    private String setMerchant(Merchant merchant, String mediaType){
        System.out.println("Merchant we are sending: " + merchant.getAccount());
        Response response = baseUrl.path("merchants").request().post(Entity.entity(merchant, mediaType));
        String output = response.readEntity(String.class);
        System.out.println("This is the result of output: " + output);
        return output;
    }
    public Merchant getMerchant(String id){
        return baseUrl.path("merchants/" + id).request().get(Merchant.class);
    }

    public int pay(Payment payment){
        Response response = baseUrl.path("merchants").path("payments").request().post(Entity.entity(payment, MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public List<MerchantPayment> getReport(String id) {
        return baseUrl.path("merchants").path("payments").path(id).request().get(new GenericType<List<MerchantPayment>>() {});
    }
}
