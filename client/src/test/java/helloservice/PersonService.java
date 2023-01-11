package helloservice;

import org.acme.Person;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class PersonService {

	WebTarget baseUrl;

	public PersonService() {
		Client client = ClientBuilder.newClient();
		baseUrl = client.target("http://localhost:8080/");
	}
	
	public Person getPersonJson() {
		return getPerson(MediaType.APPLICATION_JSON);
	}
	
	public Person getPersonXml() {
		return getPerson(MediaType.APPLICATION_XML);
	}
	
	public void setPersonJson(Person person) {
		setPerson(person, MediaType.APPLICATION_JSON);
	}
	
	public void setPersonXml(Person person) {
		setPerson(person, MediaType.APPLICATION_XML);
	}

	private Person getPerson(String mediaType) {
		return baseUrl.path("person")
				.request()
				.accept(mediaType)
				.get(Person.class);
	}
	
	private void setPerson(Person person,String mediaType) {
		baseUrl.path("person")
		        .request()
		        .put(Entity.entity(person, mediaType));
	}

}
