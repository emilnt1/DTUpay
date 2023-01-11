package helloservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/* Hint:
 * The step classes do not do the HTTP requests themselves.
 * Instead, the tests use the class HelloService, which encapsulates the 
 * HTTP requests. This abstractions help to write easier and more understandable 
 * test classes.
 */
public class HelloServiceSteps {

	String result;
	HelloService service = new HelloService();

	@When("I call the hello service")
	public void iCallTheHelloService() {
		result = service.hello();
	}

	@Then("I get the answer {string}")
	public void iGetTheAnswer(String string) {
		assertEquals(string, result);
	}

}
