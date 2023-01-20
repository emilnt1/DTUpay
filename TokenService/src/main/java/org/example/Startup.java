package org.example;

import messaging.implementations.RabbitMqQueue;

public class Startup {
    public static void main(String[] args) throws Exception {

        new Startup().startUp();


    }

    public void startUp() throws Exception {
        int maxRetries = 5;
        int retryInterval = 1000; // in milliseconds
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                System.out.println("startup");

                var mq = new RabbitMqQueue("rabbitMq");
                new TokenService(mq);
                break;
            } catch (Exception e) {
                retryCount++;
                Thread.sleep(retryInterval);
            }
        }


    }
}