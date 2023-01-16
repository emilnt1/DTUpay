package controller;

import dtu.ws.fastmoney.User;
import org.acme.Customer;
import org.acme.Database;
import org.acme.Token;

import javax.xml.crypto.Data;
import java.util.List;

public class TokenController {


    Database database = Database.getInstance();

    public List<Token> generateToken(int amount, String cid) {

        return null;
    }


    public boolean verifyToken() {

        return true;
    }




}
