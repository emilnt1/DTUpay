package controller;

import dtu.ws.fastmoney.User;
import org.acme.Customer;
import org.acme.Database;
import org.acme.Token;

import javax.xml.crypto.Data;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenController {


    Database database = Database.getInstance();

    public List<String> generateToken(int amount, String cid) {

        List<String> newList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            byte[] array = new byte[32];
            new Random().nextBytes(array);
            String token = new String(array, Charset.forName("UTF-8"));
            newList.add(token);
            database.addToken(token, cid);
        }

        return newList;
    }


    public boolean verifyToken() {

        return true;
    }




}
