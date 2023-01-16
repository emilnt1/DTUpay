package service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.acme.Customer;
import org.acme.Merchant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PaymentSteps {

    String mid, cid;
    User cUser, mUser;
    Customer customer;
    Merchant merchant;
    List<String> accountsList = new ArrayList<>();
    MerchantAPI merchantAPI = new MerchantAPI();
    CustomerAPI customerAPI = new CustomerAPI();
    BankService bank = new BankServiceService().getBankServicePort();


    @Given("a valid customer registered at dtupay")
    public void aValidCustomer() {
        cUser = new User();
        cUser.setFirstName("Tongo");
        cUser.setLastName("Nongo");
        cUser.setCprNumber("3012997334");
        customer = new Customer();
        customer.setFirstName(cUser.getFirstName());
        customer.setLastName(cUser.getLastName());
        customer.setCpr(cUser.getCprNumber());
        try{
            cid = bank.createAccountWithBalance(cUser, new BigDecimal(1000));
            accountsList.add(cid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        customer.setId(customerAPI.createAccount(cUser, cid));
    }

    @When("they want {int} tokens issued")
    public void theyWantTokensIssued(int amount) {
        Queue<String> tokens = customerAPI.getTokens(customer.getId(),amount);
        customer.setTokens(tokens);
    }

    @Then("the customer receives {int} tokens")
    public void theCustomerReceivesTokens(int amount) {
        Queue<String> queue = customer.getTokens();
        assertEquals(amount, queue.size());
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
            cid = bank.createAccountWithBalance(cUser, new BigDecimal(1000));
            accountsList.add(cid);
            mid = bank.createAccountWithBalance(mUser, new BigDecimal(1000));
            accountsList.add(mid);
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
