package org.acme;


import com.sun.mail.iap.ByteArray;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Database {
    HashMap<String, List<Payment>> paymentList;
    List<User> users;
    List<Token> tokens;

    private static Database databaseInstance = null;


    private Database(){
        paymentList = new HashMap<String, List<Payment>>();
        users = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public static Database getInstance(){
        if(databaseInstance == null){
            databaseInstance = new Database();

        }
        return databaseInstance;
    }

    public void addUser(Customer customer) {
        customer.setId(createId());
        users.add(customer);
    }

    public void addUser(Merchant merchant) {
        merchant.setId(createId());
        users.add(merchant);
    }

    private String createId() {
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
