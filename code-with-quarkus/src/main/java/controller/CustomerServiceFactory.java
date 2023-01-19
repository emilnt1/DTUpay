package controller;

import messaging.implementations.RabbitMqQueue;

public class CustomerServiceFactory {
    static CustomerService customerService = null;

    public synchronized CustomerService getService(){
        if(customerService != null){
            return customerService;
        }
        var mq = new RabbitMqQueue("localhost");
        customerService = new CustomerService(mq);
        return customerService;
    }
}
