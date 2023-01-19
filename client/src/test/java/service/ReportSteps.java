package service;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.acme.*;

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

    List<Transaction> transactions = new ArrayList<>();
    MerchantAPI merchantAPI = new MerchantAPI();
    CustomerAPI customerAPI = new CustomerAPI();

    ReportsAPI reportsAPI = new ReportsAPI();
    BankService bank = new BankServiceService().getBankServicePort();

    List<CustomerPayment> customerPayments;
    List<MerchantPayment> merchantPayments;



    @Given("a valid customer and a merchant with existing transactions")
    public void aValidCustomerWithExistingTransactions() {
        cUser = new User();
        cUser.setFirstName("Perse");
        cUser.setLastName("Frimann");
        cUser.setCprNumber("3018998765");
        customer = new Customer();
        customer.setFirstName(cUser.getFirstName());
        customer.setLastName(cUser.getLastName());
        customer.setCpr(cUser.getCprNumber());

        mUser = new User();
        mUser.setFirstName("Mochael");
        mUser.setLastName("Laudrup");
        mUser.setCprNumber("1514158475");
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
        customerPayments = customerAPI.getReport(customer.getId());
    }

    @Then("a list of the customers transactions is provided")
    public void aListOfTheCustomersTransactionsIsProvided() {
        assertFalse(customerPayments.isEmpty());

    }

    @And("the list consists of the correct transactions for the customer")
    public void theListConsistsOfTheCorrectTransactions() {
        assertEquals(1, customerPayments.size());
        assertEquals(token, customerPayments.get(0).getToken());
        assertEquals(merchant.getId(), customerPayments.get(0).getMid());
        assertEquals(new BigDecimal(100), customerPayments.get(0).getAmount());
    }


    @When("a merchant requests a list of their transactions")
    public void aMerchantRequestsAListOfTheirTransactions() {
        merchantPayments = merchantAPI.getReport(merchant.getId());
    }

    @Then("a list of a merchants transactions is provided")
    public void aListOfAMerchantsTransactionsIsProvided() {
        assertFalse(merchantPayments.isEmpty());
    }

    @And("the list consists of the correct transactions for the merchant")
    public void theListConsistsOfTheCorrectTransactionsForTheMerchant() {
        assertEquals(1, merchantPayments.size());
        assertEquals(token, merchantPayments.get(0).getToken());
        assertEquals(new BigDecimal(100), merchantPayments.get(0).getAmount());
    }

    @When("a manager requests a report of all transactions from DTUPay")
    public void aManagerRequestsAReportOfAllTransactionsFromDTUPay() {
        transactions = reportsAPI.getReport();

    }

    @Then("a list of all transactions is provided")
    public void aListOfAllTransactionsIsProvided() {
        assertFalse(transactions.isEmpty());
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
