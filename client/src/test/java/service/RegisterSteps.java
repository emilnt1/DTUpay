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
import jakarta.ws.rs.NotFoundException;
import org.acme.Customer;
import org.acme.Merchant;
import org.acme.Payment;
import org.junit.BeforeClass;
import org.junit.function.ThrowingRunnable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class RegisterSteps {

    BankService bank = new BankServiceService().getBankServicePort();
    User cuser, muser;
    String cid,mid;
    Account account;
    Customer customer;
    Merchant merchant;
    String token;
    boolean verified;
    CustomerAPI customerAPI = new CustomerAPI();
    MerchantAPI merchantAPI = new MerchantAPI();
    List<String> accountsList = new ArrayList<>();




    @Given("a customer with a registered bank account")
    public void aCustomerWithARegisteredBankAccount() {
        cuser = new User();
        cuser.setCprNumber("2803750479");
        cuser.setFirstName("Nikolai");
        cuser.setLastName("Stone");

        try{
            cid = bank.createAccountWithBalance(cuser, new BigDecimal(1000));
            accountsList.add(cid);
            System.out.println(cid);
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @And("a merchant with a registered bank account")
    public void aMerchantWithARegisteredBankAccount() {
        muser = new User();
        muser.setCprNumber("1312883790");
        muser.setFirstName("Nikolai");
        muser.setLastName("Stein");

        try{
            mid = bank.createAccountWithBalance(muser, new BigDecimal(1000));
            accountsList.add(mid);
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

    @When("a customer with a valid bank id wants to register")
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

    @When("a merchant with a valid bank id wants to register")
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


    @Given("a customer registered at DTUPay")
    public void aCustomerRegisteredAtDTUPay() {
        cuser = new User();
        cuser.setFirstName("Dinmor");
        cuser.setLastName("Birgit");
        cuser.setCprNumber("2005704030");
        customer = new Customer();
        customer.setFirstName(cuser.getFirstName());
        customer.setLastName(cuser.getLastName());
        customer.setCpr(cuser.getCprNumber());

        muser = new User();
        muser.setFirstName("karl");
        muser.setLastName("jensen");
        muser.setCprNumber("1414154433");
        merchant = new Merchant();
        merchant.setFirstName(muser.getFirstName());
        merchant.setLastName(muser.getLastName());
        merchant.setCpr(muser.getCprNumber());


        try {
            cid = bank.createAccountWithBalance(cuser, new BigDecimal(1000));
            accountsList.add(cid);
            mid = bank.createAccountWithBalance(muser, new BigDecimal(1000));
            accountsList.add(mid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        customer.setId(customerAPI.createAccount(cuser, cid));
        merchant.setId(merchantAPI.createAccount(muser, mid));

        Queue<String> tokens = customerAPI.getTokens(customer.getId(),5);
        customer.setTokens(tokens);
        token = customer.removeToken();

        Payment payment = new Payment();
        payment.setToken(token);
        payment.setMid(merchant.getId());
        payment.setAmount(new BigDecimal(100));
        merchantAPI.pay(payment);
    }


    @When("the customer requests to deregister")
    public void theCustomerRequestsToDeregister() {
        customerAPI.deleteCustomer(cid);
    }

    @Then("the customer is deleted and is no longer in the system")
    public void theCustomerIsDeletedAndIsNoLongerInTheSystem() {
        assertThrows(NotFoundException.class, () -> {
            customerAPI.getCustomer(cid);
        });


    }

    @Given("a merchant registered at DTUPay")
    public void aMerchantRegisteredAtDTUPay() {
        muser = new User();
        muser.setFirstName("Rasmus");
        muser.setLastName("Kristensen");
        muser.setCprNumber("1414151819");
        merchant = new Merchant();
        merchant.setFirstName(muser.getFirstName());
        merchant.setLastName(muser.getLastName());
        merchant.setCpr(muser.getCprNumber());

        try {
            mid = bank.createAccountWithBalance(muser, new BigDecimal(1000));
            accountsList.add(mid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @When("the merchant requests to deregister")
    public void theMerchantRequestsToDeregister() {
        merchantAPI.deleteMerchant(mid);
    }

    @Then("the merchant is deleted and is no longer in the system")
    public void theMerchantIsDeletedAndIsNoLongerInTheSystem() {
        assertThrows(NotFoundException.class, () -> {
            merchantAPI.getMerchant(mid);
        });
    }

    @After
    public void afterProcMerchant(){
        accountsList.forEach((id) -> {
            System.out.println("Retiriring account: " + id);
            try {
                bank.retireAccount(id);

            } catch (Exception e) {

                System.err.println("Result of after(merchant): " + e.getMessage());
                fail();
            }
        });
    }

}
