package helloservice;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class HelloService {

	WebTarget baseUrl;

	public HelloService() {
		Client client = ClientBuilder.newClient();
		baseUrl = client.target("http://localhost:8080/");
	}
	
	public String hello() {
		return baseUrl.path("hello").request().get(String.class);
	}
}
