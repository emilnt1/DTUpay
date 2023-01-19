package org.example;

import messaging.implementations.RabbitMqQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        new Main().startUp();


    }

    public void startUp() throws Exception{
        System.out.println("startup");

        var mq = new RabbitMqQueue("localhost");
        new MerchantService(mq);

    }
}