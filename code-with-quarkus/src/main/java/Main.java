import dtu.ws.fastmoney.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankService bank = new BankServiceService().getBankServicePort();
        String test = "hej";
        try {
            bank.getAccount(test);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
