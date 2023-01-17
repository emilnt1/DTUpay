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
import org.acme.UserPayment;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class ReportSteps {
    String mid, cid, token;
    int response, walletSize;
    User cUser, mUser;
    Customer customer;
    Merchant merchant;
    List<String> accountsList = new ArrayList<>();
    MerchantAPI merchantAPI = new MerchantAPI();
    CustomerAPI customerAPI = new CustomerAPI();
    BankService bank = new BankServiceService().getBankServicePort();

    List<UserPayment> userPayments;



    @Given("a valid customer with existing transactions")
    public void aValidCustomerWithExistingTransactions() {
        cUser = new User();
        cUser.setFirstName("Tongo");
        cUser.setLastName("Nongo");
        cUser.setCprNumber("3012997334");
        customer = new Customer();
        customer.setFirstName(cUser.getFirstName());
        customer.setLastName(cUser.getLastName());
        customer.setCpr(cUser.getCprNumber());

        mUser = new User();
        mUser.setFirstName("Kasper");
        mUser.setLastName("And");
        mUser.setCprNumber("1414157331");
        merchant = new Merchant();
        merchant.setFirstName(mUser.getFirstName());
        merchant.setLastName(mUser.getLastName());
        merchant.setCpr(mUser.getCprNumber());


        try {
            cid = bank.createAccountWithBalance(cUser, new BigDecimal(1000));
            accountsList.add(cid);
            mid = bank.createAccountWithBalance(mUser, new BigDecimal(1000));
            accountsList.add(mid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        customer.setId(customerAPI.createAccount(cUser, cid));
        merchant.setId(merchantAPI.createAccount(mUser, mid));

        Queue<String> tokens = customerAPI.getTokens(customer.getId(),5);
        customer.setTokens(tokens);
        token = customer.removeToken();

        Payment payment = new Payment();
        payment.setToken(token);
        payment.setMid(merchant.getId());
        payment.setAmount(new BigDecimal(100));
        merchantAPI.pay(payment);




    }

    @When("the customer requests a list of their transactions")
    public void theCustomerRequestsAListOfTheirTransactions() {
        userPayments = customerAPI.getReport(customer.getId());
    }

    @Then("a list of the customers transactions is provided")
    public void aListOfTheCustomersTransactionsIsProvided() {
        assertFalse(userPayments.isEmpty());

    }

    @And("the list consists of the correct transactions")
    public void theListConsistsOfTheCorrectTransactions() {
    }

    @Given("a valid merchant")
    public void aValidMerchant() {
    }

    @When("a merchant requests a list of their transactions")
    public void aMerchantRequestsAListOfTheirTransactions() {
    }

    @Then("a list of a merchants transactions is provided")
    public void aListOfAMerchantsTransactionsIsProvided() {
    }

    @When("a manager requests a report of all transactions from DTUPay")
    public void aManagerRequestsAReportOfAllTransactionsFromDTUPay() {
    }

    @Then("a list of all transactions is provided")
    public void aListOfAllTransactionsIsProvided() {
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
