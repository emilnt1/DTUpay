package controller;

import dtu.ws.fastmoney.User;
import org.acme.Customer;
import org.acme.Merchant;

public class UserController {


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
