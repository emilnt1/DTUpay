package org.acme;

import dtu.ws.fastmoney.BankServiceException;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

public class FmSteps {

    BankServiceService bank = new BankServiceService();
    String validId;
    String invalidId;
    User user;

    BigDecimal balance;


    @Given("i have a valid user {string} {string} with cpr {string}")
    public void iHaveAValidUserWithCpr(String arg0, String arg1, String arg2){
        user = new User();
        user.setFirstName(arg0);
        user.setLastName(arg1);
        user.setCprNumber(arg2);
    }

    @When("i create a new account")
    public void iCreateANewAccount() {
        try {
            validId = bank.getBankServicePort().createAccountWithBalance(user, new BigDecimal(1000));
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
        
    }

    @Then("i should get a valid account id")
    public void iShouldGetAValidAccountId() {
        assertNotNull(validId);
        System.err.println("ID is: " + validId);
    }

    @Given("i have a valid account")
    public void iHaveAValidAccount() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setCprNumber("1912981513");
        try {
            validId = bank.getBankServicePort().createAccountWithBalance(user, new BigDecimal(1000));
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }

        try {
            validId = bank.getBankServicePort().getAccount(validId).getId();
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @And("the account has been created")
    public void theAccountHasBeenCreated() {
        assertNotNull(validId);
    }

    @When("i request the balance")
    public void iRequestTheBalance() {
        try {
            balance = bank.getBankServicePort().getAccount(validId).getBalance();
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Then("i should get the balance")
    public void iShouldGetTheBalance() {
        assertEquals(1000, balance.intValue());
    }

    @After
    public void tearDown() {
        try {
            bank.getBankServicePort().retireAccount(validId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
