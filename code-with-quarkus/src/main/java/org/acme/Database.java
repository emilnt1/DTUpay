package org.acme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    HashMap<String, List<Payment>> paymentList = new HashMap<String, List<Payment>>();
    List<User> users = new ArrayList<>();
    List<Token> tokens = new ArrayList<>();


}
