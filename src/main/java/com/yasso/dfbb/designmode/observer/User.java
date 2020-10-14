package com.yasso.dfbb.designmode.observer;

public class User implements Observer {

    private String name;
    private String message;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read(){
        System.out.println(this.name + "收到推送的消息 " + this.message);
    }
}
