package org.example;

import messaging.implementations.RabbitMqQueue;

public class Startup {
    public static void main(String[] args) throws Exception {

        new Startup().startUp();






    }

    public void startUp() throws Exception{
        System.out.println("startup");

        var mq = new RabbitMqQueue("localhost:5672");
        new CustomerService(mq);

    }
}