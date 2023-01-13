package controller;

import Endpoints.HelloEndpoint;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import org.jboss.logging.Logger;

public class BankController {


    private static final Logger LOG = Logger.getLogger(BankController.class);

    BankService bank = new BankServiceService().getBankServicePort();

    public boolean verifyAccount(String id) {


        try{
            LOG.info(bank.getAccount(id).toString());
            bank.getAccount(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public void getBalance(String cid) {

    }


}

