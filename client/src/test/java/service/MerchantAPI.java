package service;

import dtu.ws.fastmoney.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.acme.Customer;
import org.acme.Merchant;

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

    public void createAccount(User user, String id){
        Merchant merchant = new Merchant("-1", user.getFirstName(), user.getLastName(), user.getCprNumber(), id);

        setMerchant(merchant, MediaType.APPLICATION_JSON);
    }

    private void setMerchant(Merchant merchant, String mediaType){
        baseUrl.path("merchants").request().post(Entity.entity(merchant, mediaType));
    }
}
