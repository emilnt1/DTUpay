package controller;

import dtu.ws.fastmoney.User;
import org.acme.Customer;
import org.acme.Database;
import org.acme.Merchant;
import org.acme.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserController {

    Database database = Database.getInstance();

    public void createCustomer(Customer customer) {
        database.addUser(customer);
    }

    public Customer getCustomer() {

        return null;
    }

    public Merchant getMerchant() {
        return null;
    }

    public User getFMUser(Customer customer) {
        User user = new User();
        user.setFirstName(customer.getFirstName());
        user.setLastName(customer.getLastName());
        user.setCprNumber(customer.getCpr());
        return user;
    }

    public User getFMUser(Merchant merchant) {
        User user = new User();
        user.setFirstName(merchant.getFirstName());
        user.setLastName(merchant.getLastName());
        user.setCprNumber(merchant.getCpr());
        return user;
    }
}
