package controller;

import messaging.implementations.RabbitMqQueue;

public class MerchantServiceFactory {
    static MerchantService merchantService = null;

    public synchronized MerchantService getService(){
        if(merchantService != null){
            return merchantService;
        }
        var mq = new RabbitMqQueue("rabbitMq");
        merchantService = new MerchantService(mq);
        return merchantService;
    }
}
