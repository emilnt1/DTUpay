package service;

import dtu.ws.fastmoney.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.acme.Customer;

public class CustomerAPI {

    WebTarget baseUrl;

    public CustomerAPI() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8080/");
    }

    /*
    public boolean verifyBankAccount(String id) {
        return baseUrl.path("accounts").request().get(boolean.class);
    }
    */

    public void createAccount(User user, String id){
        Customer customer = new Customer("-1", user.getFirstName(), user.getLastName(), user.getCprNumber(), id);

        setCustomer(customer, MediaType.APPLICATION_JSON);
    }

    private void setCustomer(Customer customer, String mediaType){
        baseUrl.path("customers").request().post(Entity.entity(customer, mediaType));
    }
}
