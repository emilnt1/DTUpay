package controller;

import Endpoints.CustomerEndpoint;
import org.acme.*;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class ReportController {

    private static final Logger LOG = Logger.getLogger(ReportController.class);
    Database database = Database.getInstance();

    public List<Transaction> getReport() {
        List<Customer> customers = database.getCustomers();
        List<Transaction> transactions = new ArrayList<>();
        for (Customer customer : customers) {
            LOG.info("Customer: " + customer.getId());
            List<Payment> customerPayments;
            customerPayments = database.getPayments(customer.getId());
            if (customerPayments.equals(null)) {
                break;
            }
            LOG.info("Customerpayments: " + customerPayments);
            for (Payment payment: customerPayments) {
                Transaction transaction = new Transaction();
                transaction.setCid(customer.getId());
                transaction.setMid(((CustomerPayment) payment).getMid());
                transaction.setAmount(payment.getAmount());
                transaction.setToken(payment.getToken());
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    public List<CustomerPaymentDTO> getCustomerReport(String id) {
        List<Payment> payments = database.getPayments(id);

        return convertCustomerPaymentDTO(payments);
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
}
