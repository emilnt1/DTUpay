package controller;

import org.acme.*;

import java.util.ArrayList;
import java.util.List;

public class ReportController {


    Database database = Database.getInstance();

    public List<Transaction> getReport() {
        List<Customer> customers = database.getCustomers();
        List<Transaction> transactions = new ArrayList<>();
        for (Customer customer : customers) {
            List<Payment> customerPayments = database.getPayments(customer.getId());


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

}
