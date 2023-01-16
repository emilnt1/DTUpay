package service;

import com.sun.xml.fastinfoset.util.StringArray;
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

import java.util.LinkedList;
import java.util.List;

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

    public String createAccount(User user, String accountId){
        Customer customer = new Customer();
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setAccount(accountId);
        customer.setCpr(user.getCprNumber());
        customer.setTokens(new LinkedList<>());
        customer.setId("-1");

        return setCustomer(customer, MediaType.APPLICATION_JSON);
    }

    public List<String> getTokens(String id, int amount){


        return null;
    }

    private String setCustomer(Customer customer, String mediaType){

        System.out.println("Customer we are sending: " + customer.getAccount());
        Response response = baseUrl.path("customers").request().post(Entity.entity(customer, mediaType));
        String output = response.readEntity(String.class);
        System.out.println("This is the result of output: " + output);
        return output;
    }

    public Customer getCustomer(String id){
        return baseUrl.path("customers/" + id).request().get(Customer.class);
    }
}
