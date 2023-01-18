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
                String token = buildString();
                database.addToken(token, cid);
            }
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
        return newList;
    }

    private String buildString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int index = (int) (characters.length()* Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }





}
