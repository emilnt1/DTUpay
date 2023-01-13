package controller;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;

public class BankController {

    BankService bank = new BankServiceService().getBankServicePort();

    public boolean verifyAccount(String id) {
        try{
            bank.getAccount(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public void getBalance(String cid) {

    }


}

