package controller;

import messaging.implementations.RabbitMqQueue;

public class UserServiceFactory {
    static UserService userService = null;

    public synchronized UserService getService(){
        if(userService != null){
            return userService;
        }
        var mq = new RabbitMqQueue("localhost");
        userService = new UserService(mq);
        return userService;
    }
}
