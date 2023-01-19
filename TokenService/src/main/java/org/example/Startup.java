package org.example;

import messaging.implementations.RabbitMqQueue;

public class Startup {
    public static void main(String[] args) throws Exception {

        new Startup().startUp();






    }

    public void startUp() throws Exception{
        System.out.println("startup");

        var mq = new RabbitMqQueue("10.5.0.5");
        new org.example.TokenService(mq);

    }
}