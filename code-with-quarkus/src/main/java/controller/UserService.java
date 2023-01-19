package controller;

import dtu.ws.fastmoney.User;
import lombok.NoArgsConstructor;
import messaging.Event;
import messaging.MessageQueue;
import org.acme.Customer;
import org.acme.Database;
import org.acme.Merchant;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@NoArgsConstructor
public class UserService {


    private MessageQueue queue;

    private CompletableFuture<List<String>> pendingTokens;

    Database database = Database.getInstance();

    public String createCustomer(Customer customer) {
        return database.addUser(customer);
    }

    public Customer getCustomer(String id) {
        return (Customer) database.getUser(id);
    }

    public String createMerchant(Merchant merchant){
        return database.addUser(merchant);
    }

    public Merchant getMerchant(String id) {
        return (Merchant) database.getUser(id);
    }

    public void deleteUser(String id) {
        database.deleteUser(id);
    }

    public UserService(MessageQueue q){
        queue = q;
        queue.addHandler("TokensIssued", this::handleTokensIssued);
    }

    public List<String> issueTokens(int amount, String cid){
        pendingTokens = new CompletableFuture<>();
        Event event = new Event("TokensRequested", new Object[]{amount});
        queue.publish(event);
        database.addToken(cid, pendingTokens.join());
        return pendingTokens.join();
    }

    public void handleTokensIssued(Event e){
        var tokens = e.getArgument(0, List.class);
        pendingTokens.complete(tokens);

    }

    public User getFMUser(Customer customer) {
        User user = new User();
        user.setFirstName(customer.getFirstName());
        user.setLastName(customer.getLastName());
        user.setCprNumber(customer.getCpr());
        return user;
    }

    public User getFMUser(Merchant merchant) {
        User user = new User();
        user.setFirstName(merchant.getFirstName());
        user.setLastName(merchant.getLastName());
        user.setCprNumber(merchant.getCpr());
        return user;
    }
}
