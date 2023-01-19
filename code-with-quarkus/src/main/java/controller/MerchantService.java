package controller;

import lombok.NoArgsConstructor;
import messaging.Event;
import messaging.MessageQueue;
import org.acme.Customer;
import org.acme.CustomerDTO;
import org.acme.Database;
import org.acme.Merchant;

import java.util.concurrent.CompletableFuture;

@NoArgsConstructor
public class MerchantService {

    private MessageQueue queue;
    private CompletableFuture<Merchant> promisedMerchant;

    Database database = Database.getInstance();

    public MerchantService(MessageQueue q){
        queue = q;
        queue.addHandler("MerchantCreated", this::handleMerchantRegistered);
    }

    public Merchant register(Merchant m){
        promisedMerchant = new CompletableFuture<>();
        Event event = new Event("CreateMerchant", new Object[]{m});
        queue.publish(event);
        database.addUser(promisedMerchant.join());
        return promisedMerchant.join();
    }

    public void handleMerchantRegistered(Event e){
        var m = e.getArgument(0, Merchant.class);
        promisedMerchant.complete(m);
    }

}