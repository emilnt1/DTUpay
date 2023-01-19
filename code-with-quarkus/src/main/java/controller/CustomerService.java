package controller;

import lombok.NoArgsConstructor;
import messaging.Event;
import messaging.MessageQueue;
import org.acme.Customer;
import org.acme.CustomerDTO;
import org.acme.Database;

import java.util.concurrent.CompletableFuture;

@NoArgsConstructor
public class CustomerService {

    private MessageQueue queue;
    private CompletableFuture<Customer> promisedCustomer;

    Database database = Database.getInstance();

    public CustomerService(MessageQueue q){
        queue = q;
        queue.addHandler("CustomerCreated", this::handleCustomerRegistered);
    }

    public Customer register(Customer c){
        promisedCustomer = new CompletableFuture<>();
        Event event = new Event("CreateCustomer", new Object[]{c});
        queue.publish(event);
        database.addUser(promisedCustomer.join());
        return promisedCustomer.join();
    }

    public void handleCustomerRegistered(Event e){
        var c = e.getArgument(0, Customer.class);
        promisedCustomer.complete(c);
    }

}