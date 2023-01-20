package org.example;

import messaging.Event;
import messaging.MessageQueue;

public class CustomerService {

    MessageQueue queue;

    public CustomerService(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("CreateCustomer", this::CreateCustomer);
    }

    public void CreateCustomer(Event e) {
        var customer = e.getArgument(0, Customer.class);
        customer.setId(createId());
        Event event = new Event("CustomerCreated", new Object[] { customer });
        queue.publish(event);
    }
    public void GetCustomer(Event e) {

    }
    public void DeleteCustomer(Event e) {

    }


    private String createId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            int index = (int) (characters.length()* Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
