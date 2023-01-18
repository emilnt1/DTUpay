package org.acme;


import javax.ws.rs.NotFoundException;
import java.nio.charset.Charset;
import java.util.*;

public class Database {
    HashMap<String, List<Payment>> paymentList;
    List<User> users;
    HashMap<String, String> tokens;

    private static Database databaseInstance = null;


    private Database(){
        paymentList = new HashMap<String, List<Payment>>();
        users = new ArrayList<>();
        tokens = new HashMap<String, String>();
    }

    public static Database getInstance(){
        if(databaseInstance == null){
            databaseInstance = new Database();

        }
        return databaseInstance;
    }

    public String addUser(Customer customer) {
        customer.setId(createId());
        users.add(customer);
        return customer.id;
    }

    public String addUser(Merchant merchant) {
        merchant.setId(createId());
        users.add(merchant);
        return merchant.id;
    }

    private String createId() {
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    public Object getUser(String id) {
        for (User user: users
             ) {
            if (user.id.equals(id)) {
                    return user;
            }
        }
        throw new NotFoundException("User not found");
    }

    public void addToken(String token, String cid){
        tokens.put(token, cid);
    }

    public String getCidFromToken(String token){
        return tokens.get(token);
    }

    public void addPayment(Payment payment, String id) {
        if (paymentList.containsKey(id)) {
            paymentList.get(id).add(payment);
        } else {
            List<Payment> payments = new ArrayList<>();
            payments.add(payment);
            paymentList.put(id, payments);
        }
    }
    public String getAccountFromId(String id) {
        for (User user: users) {
            if (user.id.equals(id)) {
                return user.getAccountId();
            }
        }
        throw new NotFoundException("User not found");
    }
    public boolean checkAccountExist(String id) {
        for (User user: users) {
            if (Objects.equals(user.id, id)) {
                return true;
            }
        }
        return false;
    }

    public void removeToken(String token) {
        tokens.remove(token);
    }

    public List<Payment> getPayments(String id) {
        return paymentList.get(id);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (User user: users) {
            if (user instanceof Customer) {
                customers.add((Customer) user);
            }
        }
        return customers;
    }
}
