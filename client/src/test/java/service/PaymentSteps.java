package service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.acme.Customer;
import org.acme.Merchant;
import org.junit.BeforeClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PaymentSteps {

    String mid, cid;
    User cUser, mUser;
    Customer customer;
    Merchant merchant;
    List<String> accounts = new ArrayList<>();
    MerchantAPI merchantAPI = new MerchantAPI();
    CustomerAPI customerAPI = new CustomerAPI();
    BankService Bank = new BankServiceService().getBankServicePort();


    @Given("a valid customer registered at dtupay")
    public void aValidCustomer() {
        cUser.setFirstName("Axel");
        cUser.setLastName("Dammen");
        cUser.setCprNumber("1213140595");
        customer.setFirstName("Axel");
        customer.setLastName("Dammen");
        customer.setCpr("1213140595");
        try{
            cid = Bank.createAccountWithBalance(cUser, new BigDecimal(1000));
            accounts.add(cid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        customer.setId(customerAPI.createAccount(cUser, cid));
    }

    @When("they want {int} tokens issued")
    public void theyWantTokensIssued(int amount) {
        customerAPI.getTokens(customer.getId(),amount);
    }

    @Then("the customer receives {int} tokens")
    public void theCustomerReceivesTokens(int amount) {
        assertEquals(amount, customer.getTokens().size());
    }

    @Given("a valid customer and merchant")
    public void aValidCustomerAndMerchant() {
        cUser.setFirstName("Axel");
        cUser.setLastName("Dammen");
        cUser.setCprNumber("1213140595");
        customer.setFirstName("Axel");
        customer.setLastName("Dammen");
        customer.setCpr("1213140595");
        mUser.setFirstName("Diller");
        mUser.setLastName("Mand");
        mUser.setCprNumber("1214150595");
        merchant.setFirstName("Diller");
        merchant.setLastName("Mand");
        merchant.setCpr("1214150595");


        try{
            cid = Bank.createAccountWithBalance(cUser, new BigDecimal(1000));
            accounts.add(cid);
            mid = Bank.createAccountWithBalance(mUser, new BigDecimal(1000));
            accounts.add(mid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        customer.setId(customerAPI.createAccount(cUser, cid));
        merchant.setId(merchantAPI.createAccount(mUser,mid));

    }

    @When("a customer wants to make a payment to a merchant")
    public void aCustomerWantsToMakeAPaymentToAMerchant() {
    }

    @Then("the customer presents a token to the merchant")
    public void theCustomerPresentsATokenToTheMerchant() {
    }

    @And("the merchant makes the payment")
    public void theMerchantMakesThePayment() {
    }
}
