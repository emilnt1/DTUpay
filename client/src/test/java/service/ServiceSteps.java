package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import fastmoney.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.acme.Customer;
import org.junit.jupiter.api.AfterEach;

import java.math.BigDecimal;

/* Hint:
 * The step classes do not do the HTTP requests themselves.
 * Instead, the tests use the class HelloService, which encapsulates the
 * HTTP requests. This abstractions help to write easier and more understandable
 * test classes.
 */
public class ServiceSteps{


    BankService bank;
    User user;
    String id;
    Account account;


    @Before
    public void createBankAccount(){
        bank = new BankServiceService().getBankServicePort();
        user = new User();
        account = new Account();
    }

    @Given("a DTU Pay customer with a fastmoney bank account with first name {string} last name {string} and CPR {string} and balance {int}")
    public void aDTUPayCustomerWithAFastmoneyBankAccountWithFirstNameLastNameAndCPRAndBalance(String firstname, String lastname, String cpr, int balance) {
        try{
            account = bank.getAccount(id);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @When("the customer wants to link their account")
    public void theCustomerWantsToLinkTheirAccount() {


    }

    @Then("DTU pay is contacted and user is created")
    public void dtuPayIsContactedAndUserIsCreated() {
    }

    @When("{string} {string} with CPR {string} wants an account")
    public void heHaveABalance(String fname, String lname, String cpr) {
        user = new User();
        user.setLastName(lname);
        user.setFirstName(fname);
        user.setCprNumber(cpr);
    }

    @Then("DTUpay creates an user at FM")
    public void dtupayCreatesAnUser() {
        try {
            id = bank.createAccountWithBalance(user, new BigDecimal(0));
            //Register service
            //HTTP call
        } catch (Exception e)
        {
            //Fails if cant create
            System.err.println(e.getMessage());
        }
    }
    
    @After
    public void afterProc()
    {
        try {
            bank.retireAccount(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
