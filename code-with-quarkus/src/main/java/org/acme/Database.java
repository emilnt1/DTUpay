package org.acme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    HashMap<String, List<Payment>> paymentList = new HashMap<String, List<Payment>>();
    List<User> users;
    List<Token> tokens;

    private static Database instance = null;

    private Database() {
        users = new ArrayList<User>();
        tokens = new ArrayList<Token>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }


}
