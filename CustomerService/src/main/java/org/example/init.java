package org.example;

import messaging.implementations.RabbitMqQueue;

import java.io.IOException;

public class init {
    public static void main(String[] args) throws Exception {

        new init().startUp();






    }

    public void startUp() throws Exception{

        int maxRetries = 5;
        int retryInterval = 1000; // in milliseconds
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                System.out.println("startup");

                var mq = new RabbitMqQueue("rabbitMq");
                new CustomerService(mq);
                break;
            } catch (Exception e) {
                retryCount++;
                Thread.sleep(retryInterval);
            }
        }



    }
}