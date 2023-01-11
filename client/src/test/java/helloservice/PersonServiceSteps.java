package helloservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.acme.Person;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/* Hint:
 * The step classes do not do the HTTP requests themselves.
 * Instead, the tests use the class PersonService, which encapsulates the 
 * HTTP requests. This abstractions help to write easier and more understandable 
 * test classes.
 */
public class PersonServiceSteps {

	Person person;
	PersonService service = new PersonService();

	@When("I call the hello service to get person via Xml")
	public void iCallTheHelloServiceToGetPersonViaXml() {
		person = service.getPersonJson();
	}

	@Then("I get a person with name {string} and address {string}")
	public void iGetAPersonWithNameAndAddress(String name, String address) {
		assertEquals(name, person.getName());
		assertEquals(address, person.getAddress());
	}

	@When("I call the hello service to get person via Json")
	public void iCallTheHelloServiceToGetPersonViaJson() {
		person = service.getPersonXml();
	}
	
	@When("I update the person with name {string} and address {string} using JSON")
	public void iUpdateThePersonWithNameAndAddressUsingJSON(String name, String address) {
	    service.setPersonJson(new Person(name,address));
	}
	
	@When("I update the person with name {string} and address {string} using XML")
	public void iUpdateThePersonWithNameAndAddressUsingXML(String name, String address) {
	    service.setPersonXml(new Person(name,address));
	}

}
