package controller;

import Endpoints.CustomerEndpoint;
import Exceptions.IllegalArgumentException;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import org.acme.*;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    Database database = Database.getInstance();
    BankService bank = new BankServiceService().getBankServicePort();
    private static final Logger LOG = Logger.getLogger(PaymentController.class);

    public void makePayment(PaymentDTO paymentDTO) throws Exception {
        if (paymentDTO.getAmount().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        String cid = database.getCidFromToken(paymentDTO.getToken());
        String cAccountId = database.getAccountFromId(cid);
        String mAccountId = database.getAccountFromId(paymentDTO.getMid());
        //String description = cid + " paid to " + paymentDTO.getMid() + " " + paymentDTO.getAmount() + " DKK";

        LOG.info("Customerid: " + cid + " Customer account: " + cAccountId);
        LOG.info("Merchantid: " + paymentDTO.getMid() + " Merchant account: " + mAccountId);
        try {
            bank.transferMoneyFromTo(cAccountId, mAccountId, paymentDTO.getAmount(),"description");
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        CustomerPayment customerPayment = new CustomerPayment();
        MerchantPayment merchantPayment = new MerchantPayment();
        customerPayment.setMid(paymentDTO.getMid());
        customerPayment.setAmount(paymentDTO.getAmount());
        customerPayment.setToken(paymentDTO.getToken());
        merchantPayment.setAmount(paymentDTO.getAmount());
        merchantPayment.setToken(paymentDTO.getToken());
        database.addPayment(customerPayment, cid);
        database.addPayment(merchantPayment, paymentDTO.getMid());
        database.removeToken(paymentDTO.getToken());
    }



    public List<CustomerPaymentDTO> getCustomerReport(String id) {
        List<Payment> payments = database.getPayments(id);

        return convertCustomerPaymentDTO(payments);
    }

    public List<CustomerPaymentDTO> convertCustomerPaymentDTO(List<Payment> payments) {
        List<CustomerPaymentDTO> customerPaymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            if (payment instanceof CustomerPayment) {
                CustomerPaymentDTO customerPaymentDTO = new CustomerPaymentDTO();
                customerPaymentDTO.setMid(((CustomerPayment) payment).getMid());
                customerPaymentDTO.setAmount(payment.getAmount());
                customerPaymentDTO.setToken(payment.getToken());
                customerPaymentDTOS.add(customerPaymentDTO);
            }
        }
        return customerPaymentDTOS;
    }

    public List<MerchantPaymentDTO> getMerchantReport(String id) {
        List<Payment> payments = database.getPayments(id);

        return convertMerchantPaymentDTO(payments);
    }

    private List<MerchantPaymentDTO> convertMerchantPaymentDTO(List<Payment> payments) {
        List<MerchantPaymentDTO> merchantPaymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            if (payment instanceof MerchantPayment) {
                MerchantPaymentDTO merchantPaymentDTO = new MerchantPaymentDTO();
                merchantPaymentDTO.setToken(payment.getToken());
                merchantPaymentDTO.setAmount(payment.getAmount());
                merchantPaymentDTOS.add(merchantPaymentDTO);
            }
        }
        return merchantPaymentDTOS;
    }
}
