package controller;

import Endpoints.CustomerEndpoint;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import org.acme.*;
import org.jboss.logging.Logger;

import java.math.BigDecimal;

public class PaymentController {
    Database database = Database.getInstance();
    BankService bank = new BankServiceService().getBankServicePort();
    private static final Logger LOG = Logger.getLogger(PaymentController.class);

    public void makePayment(PaymentDTO paymentDTO) throws Exception{
        String cid = database.getCidFromToken(paymentDTO.getToken());
        String cAccountId = database.getAccountFromId(cid);
        String mAccountId = database.getAccountFromId(paymentDTO.getMid());
        //String description = cid + " paid to " + paymentDTO.getMid() + " " + paymentDTO.getAmount() + " DKK";

        LOG.info("Customerid: " + cid + " Customer account: " + cAccountId);
        LOG.info("Merchantid: " + paymentDTO.getMid() + " Merchant account: " + mAccountId);
        try {
            bank.transferMoneyFromTo(cAccountId, mAccountId, new BigDecimal(paymentDTO.getAmount()),"description");
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        CustomerPayment customerPayment = new CustomerPayment();
        MerchantPayment merchantPayment = new MerchantPayment();
        customerPayment.setMid(paymentDTO.getMid());
        customerPayment.setAmount(new BigDecimal(paymentDTO.getAmount()));
        customerPayment.setToken(paymentDTO.getToken());
        merchantPayment.setAmount(new BigDecimal(paymentDTO.getAmount()));
        merchantPayment.setToken(paymentDTO.getToken());
        database.addPayment(customerPayment, cid);
        database.addPayment(merchantPayment, paymentDTO.getMid());

    }

    public void getReport(String id){}

    public void getReport(){}



}
