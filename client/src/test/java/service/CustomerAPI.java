package service;

import dtu.ws.fastmoney.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Customer;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.BasicResponseHandler;

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
        Response response = baseUrl.path("customers").request().post(Entity.entity(customer, mediaType));
        String output = response.readEntity(String.class);
        System.out.println("This is the result of output: " + output);
    }
}
