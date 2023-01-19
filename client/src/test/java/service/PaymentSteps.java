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
import org.acme.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PaymentSteps {

    String mid, cid, token;
    int response, walletSize;
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
        cUser.setFirstName("Tongono");
        cUser.setLastName("Nongono");
        cUser.setCprNumber("2812997334");
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
        cUser = new User();
        cUser.setFirstName("Perle");
        cUser.setLastName("Frismann");
        cUser.setCprNumber("2412998765");
        customer = new Customer();
        customer.setFirstName(cUser.getFirstName());
        customer.setLastName(cUser.getLastName());
        customer.setCpr(cUser.getCprNumber());
        mUser = new User();
        mUser.setFirstName("Mochael");
        mUser.setLastName("Landdrup");
        mUser.setCprNumber("1414168475");
        merchant = new Merchant();
        merchant.setFirstName(mUser.getFirstName());
        merchant.setLastName(mUser.getLastName());
        merchant.setCpr(mUser.getCprNumber());


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

    @When("a customer presents a token to a merchant")
    public void aCustomerWantsToMakeAPaymentToAMerchant() {
        System.out.println("Customer id: " + customer.getId());
        Queue<String> tokens = customerAPI.getTokens(customer.getId(),5);
        walletSize = tokens.size();
        customer.setTokens(tokens);
        token = customer.removeToken();


    }

    @And("the merchant makes the payment of {float} kr")
    public void theMerchantMakesThePayment(float amount) {
        Payment payment = new Payment();
        payment.setToken(token);
        payment.setMid(merchant.getId());
        payment.setAmount(new BigDecimal(amount));
        response = merchantAPI.pay(payment);

    }

    @Then("the payment is registered with {int}")
    public void theCustomerReceivesAReceipt(int status) {
        assertEquals(status, response);
    }

    @Then("the payment fails with error code {int}")
    public void thePaymentFailsWithErrorCode(int errorCode) {
        assertEquals(errorCode, response);
    }

    @And("the token is removed from the customer")
    public void theTokenIsRemovedFromTheCustomer() {
        assertEquals(walletSize-1, customer.getTokens().size());
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
