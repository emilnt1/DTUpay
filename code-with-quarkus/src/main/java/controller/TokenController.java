package controller;

import org.acme.Database;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenController {


    Database database = Database.getInstance();

    public List<String> generateToken(int amount, String cid) {
        if (amount < 1 || amount > 5) {
            throw new IllegalArgumentException("Amount must between 1 and 5");
        }

        List<String> newList = new ArrayList<>();
        if (database.checkAccountExist(cid)) {
            for (int i = 0; i < amount; i++) {
                byte[] array = new byte[32];
                new Random().nextBytes(array);
                String token = new String(array, Charset.forName("UTF-8"));
                newList.add(token);
                database.addToken(token, cid);
            }
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
        return newList;
    }


    public boolean verifyToken() {

        return true;
    }




}
