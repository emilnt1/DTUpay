package org.example;

import messaging.Event;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TokenService {
    MessageQueue queue;

    public TokenService(MessageQueue mq){
        this.queue = mq;
        this.queue.addHandler("TokensRequested", this::handleTokensRequested);


    }


    public void handleTokensRequested(Event e){
        var amount = e.getArgument(0,Integer.class);
        List<String> tokens = generateToken(amount);

        Event event = new Event("TokensIssued", new Object[] {tokens});
        queue.publish(event);

    }


    public List<String> generateToken(int amount) {
        if (amount < 1 || amount > 5) {
            throw new IllegalArgumentException("Amount must between 1 and 5");
        }
        List<String> newList = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                String token = buildString();
                newList.add(token);
            }
        return newList;
    }

    private String buildString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int index = (int) (characters.length()* Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

}
