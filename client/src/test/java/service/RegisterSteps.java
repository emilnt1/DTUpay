package service;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RegisterSteps {

    BankService bank = new BankServiceService().getBankServicePort();
    User user;
    String id;
    Account account;
    boolean verified;
    CustomerAPI customerAPI = new CustomerAPI();


    @Before
    public void createBankAccount(){
        user = new User();
        user.setCprNumber("0602971234");
        user.setFirstName("Oliver");
        user.setLastName("Fiedler");
        try{
            id = bank.createAccountWithBalance(user, new BigDecimal(1000));
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }

    }

    @When("I call the bank to get the account")
    public void iGetAValidBankIdAndCheckTheName() {
        try{
            account = bank.getAccount(id);
        } catch(Exception e){
            System.out.println("Result of getting bank account: " + e.getMessage());
            fail();
        }
    }

    @Then("I get an account with firstname {string}, lastname {string} and CPR {string}")
    public void iWouldGetFirstnameLastnameAndCPR(String firstname, String lastname, String cpr) {
        assertEquals(firstname, account.getUser().getFirstName());
        assertEquals(lastname, account.getUser().getLastName());
        assertEquals(cpr, account.getUser().getCprNumber());
    }

    @Given("a customer with a valid bank id")
    public void aCustomerWithAValidBankId() {
        if(id == null){
            fail();
        }
    }


    @Then("the customer is registered at DTUPay")
    public void theCustomerIsRegisteredAtDTUPay() {
        customerAPI.createAccount(user, id);


    }

    @When("a customer with an invalid bank id is registered")
    public void aCustomerWithAnInvalidBankIdIsRegistered() {
    }

    @Then("the customer is not registered at DTUPay")
    public void theCustomerIsNotRegisteredAtDTUPay() {
    }

    @When("a merchant with a valid bank id is registered")
    public void aMerchantWithAValidBankIdIsRegistered() {
    }

    @Then("the merchant is registered at DTUPay")
    public void theMerchantIsRegisteredAtDTUPay() {
    }

    @When("a merchant with an invalid bank id is registered")
    public void aMerchantWithAnInvalidBankIdIsRegistered() {
    }

    @Then("the merchant is not registered at DTUPay")
    public void theMerchantIsNotRegisteredAtDTUPay() {
    }

    @After
    public void afterProc()
    {
        try {
            bank.retireAccount(id);
        } catch (Exception e) {

            System.err.println("Result of after: " + e.getMessage());
            fail();
        }
    }



}
