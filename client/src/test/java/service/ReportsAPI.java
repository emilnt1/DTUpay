package service;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import org.acme.Transaction;

import java.util.List;

public class ReportsAPI {
    WebTarget baseUrl;
    public ReportsAPI() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8080/");
    }

    public List<Transaction> getReport () {
        return baseUrl.path("reports").request().get(new GenericType<List<Transaction>>() {});
    }
}
