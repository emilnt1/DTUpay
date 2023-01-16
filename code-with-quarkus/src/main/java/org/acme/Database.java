package org.acme;


import com.sun.mail.iap.ByteArray;

import javax.ws.rs.NotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
}
