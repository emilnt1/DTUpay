package org.acme;

import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
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
        Assert.assertEquals("John", user.getFirstName());
    }

    @When("i create a new account")
    public void iCreateANewAccount() {
        try {
            validId = bank.getBankServicePort().createAccountWithBalance(user, new BigDecimal(1000));
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
        
    }

    @Then("i should get a valid account id")
    public void iShouldGetAValidAccountId() {
        Assert.assertNotNull(validId);
        System.err.println("ID is: " + validId);
    }

    @Given("i have an invalid user")
    public void iHaveAnInvalidUser() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setCprNumber("13");
    }

    @Then("i should get an error")
    public void iShouldGetAnError() {
        try {
            invalidId = bank.getBankServicePort().createAccountWithBalance(user, new BigDecimal(1000));
            Assert.fail();
        } catch (Exception e){
            Assert.assertTrue(true);
        }
    }

    @Given("i have a valid account")
    public void iHaveAValidAccount() {
        try {
            validId = bank.getBankServicePort().getAccount(validId).getId();
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @And("the account has been created")
    public void theAccountHasBeenCreated() {
        Assert.assertNotNull(validId);
    }

    @When("i request the balance")
    public void iRequestTheBalance() {
        try {
            balance = bank.getBankServicePort().getAccount(validId).getBalance();
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Then("i should get the balance")
    public void iShouldGetTheBalance() {
        Assert.assertEquals(1000, balance.intValue());
    }

    @AfterAll
    public void tearDown() {
        try {
            bank.getBankServicePort().retireAccount(validId);
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
