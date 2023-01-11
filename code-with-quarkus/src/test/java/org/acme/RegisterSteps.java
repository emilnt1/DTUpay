package org.acme;

import controller.UserController;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.acme.Customer;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterSteps {
    UserController userController = new UserController();
    Customer customer = new Customer();
    Merchant merchant = new Merchant();
    BankService bank = new BankServiceService().getBankServicePort();
    String customerId;
    String merchantId;
    Boolean successful = false;
    @Given("a DTU Pay customer with id {string}")
    public void aDTUPayCustomerWithId(String cId) {
        customer.setId(cId);
        customer.setFirstName("testy");
        customer.setLastName("mctestface");
        customer.setCpr("0602971234");
    }

    @When("the customer wants to link their account with a balance of {int}")
    public void theCustomerWantsToLinkTheirAccountWithABalanceOf(int amount) {
        User user = userController.getFMUser(customer);
        try {
            customerId = bank.createAccountWithBalance(user, new BigDecimal(amount));
            System.out.println("Customerid: " + customerId);
            successful = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Then("the bank is contacted and the DTU Pay account is created")
    public void theBankIsContactedAndTheDTUPayAccountIsCreated() {
        assertTrue(successful);
    }

    @Given("a DTU Pay Merchant with id {string}")
    public void aDTUPayMerchantWithId(String mId) {
        merchant.setId(mId);
        merchant.setFirstName("Test");
        merchant.setLastName("McmerchantTestFace");
        merchant.setCpr("2312981234");
    }

    @When("the merchant want to link their account with a balance of {int}")
    public void theMerchantWantToLinkTheirAccountWithABalanceOf(int amount) {
        User user = userController.getFMUser(merchant);
        try {
            merchantId = bank.createAccountWithBalance(user, new BigDecimal(amount));
            System.out.println("merchantid " + merchantId);
            successful = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @And("the customer account is deleted")
    public void theCustomerAccountIsDeleted() {
        try {
            bank.retireAccount(customerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @And("the merchant account is deleted")
    public void theMerchantAccountIsDeleted() {
        try {
            bank.retireAccount(merchantId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
