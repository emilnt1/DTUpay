package org.example;

import messaging.Event;
import messaging.MessageQueue;

public class MerchantService {


    MessageQueue queue;

    public MerchantService(MessageQueue q) {
        this.queue = q;
        this.queue.addHandler("CreateMerchant", this::CreateMerchant);
        this.queue.addHandler("GetMerchant", this::GetMerchant);
        this.queue.addHandler("DeleteMerchant", this::DeleteMerchant);
    }
    public void CreateMerchant(Event e) {
        var merchant = e.getArgument(0, Merchant.class);
        merchant.setId(createId());
        Event event = new Event("MerchantCreated", new Object[] { merchant });
        queue.publish(event);
    }
    public void GetMerchant(Event e) {

    }
    public void DeleteMerchant(Event e) {

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
