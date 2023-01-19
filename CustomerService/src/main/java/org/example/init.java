package org.example;

import messaging.implementations.RabbitMqQueue;

public class init {
    public static void main(String[] args) throws Exception {

        new init().startUp();






    }

    public void startUp() throws Exception{
        System.out.println("startup");

        var mq = new RabbitMqQueue("localhost");
        new CustomerService(mq);

    }
}