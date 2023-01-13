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
import org.acme.Customer;
import org.acme.Merchant;

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
        cuser.setCprNumber("1104981234");
        cuser.setFirstName("Hans Christian");
        cuser.setLastName("Basse");

        try{
            cid = bank.createAccountWithBalance(cuser, new BigDecimal(1000));
            System.out.println(cid);
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }



    }

    @Before
    public void createMerchantBankAccount(){
        muser = new User();
        muser.setCprNumber("2312981234");
        muser.setFirstName("Emil");
        muser.setLastName("Bassemand");

        try{
            mid = bank.createAccountWithBalance(muser, new BigDecimal(1000));
            System.out.println(mid);
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
        System.out.println(cid);
        String newid = customerAPI.createAccount(cuser, cid);
        Customer newCustomer = customerAPI.getCustomer(newid);
        assertEquals(cuser.getFirstName(), newCustomer.getFirstName());
        assertEquals(cid, newCustomer.getAccount());
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
        System.out.println(mid);
        String newid = merchantAPI.createAccount(muser, mid);
        System.out.println("Merchant id from DTU Pay: " + newid);
        Merchant newMerchant = merchantAPI.getMerchant(newid);
        assertEquals(muser.getFirstName(), newMerchant.getFirstName());
        assertEquals(mid, newMerchant.getAccount());
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
