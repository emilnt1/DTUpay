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
    User cuser, muser;
    String cid,mid;
    Account account;
    boolean verified;
    CustomerAPI customerAPI = new CustomerAPI();
    MerchantAPI merchantAPI = new MerchantAPI();


    @Before
    public void createCustomerBankAccount(){
        cuser = new User();
        cuser.setCprNumber("0602971234");
        cuser.setFirstName("Oliver");
        cuser.setLastName("Fiedler");

        try{
            cid = bank.createAccountWithBalance(cuser, new BigDecimal(1000));
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }



    }

    @Before
    public void createMerchantBankAccount(){
        muser = new User();
        muser.setCprNumber("0808981234");
        muser.setFirstName("Kasper");
        muser.setLastName("Skov");

        try{
            mid = bank.createAccountWithBalance(muser, new BigDecimal(1000));
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @When("I call the bank to get the account")
    public void iGetAValidBankIdAndCheckTheName() {
        try{
            account = bank.getAccount(cid);
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
        if(cid == null){
            fail();
        }
    }


    @Then("the customer is registered at DTUPay")
    public void theCustomerIsRegisteredAtDTUPay() {
        customerAPI.createAccount(cuser, cid);


    }

    @When("a customer with an invalid bank id is registered")
    public void aCustomerWithAnInvalidBankIdIsRegistered() {
    }

    @Then("the customer is not registered at DTUPay")
    public void theCustomerIsNotRegisteredAtDTUPay() {
    }

    @Given("a merchant with a valid bank id")
    public void aMerchantWithAValidBankId() {
        if(mid == null) {
            fail();
        }
    }

    @Then("the merchant is registered at DTUPay")
    public void theMerchantIsRegisteredAtDTUPay() {
        merchantAPI.createAccount(muser, mid);
    }

    @When("a merchant with an invalid bank id is registered")
    public void aMerchantWithAnInvalidBankIdIsRegistered() {
    }

    @Then("the merchant is not registered at DTUPay")
    public void theMerchantIsNotRegisteredAtDTUPay() {
    }

    @After
    public void afterProcCustomer()
    {
        try {
            bank.retireAccount(cid);
        } catch (Exception e) {

            System.err.println("Result of after(customer): " + e.getMessage());
            fail();
        }

    }

    @After
    public void afterProcMerchant(){
        try {
            bank.retireAccount(mid);
        } catch (Exception e) {

            System.err.println("Result of after(merchant): " + e.getMessage());
            fail();
        }
    }



}
