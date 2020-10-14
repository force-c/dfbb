package com.yasso.dfbb.designmode.observer;

import java.util.ArrayList;
import java.util.List;

public class WeChatServer implements Observerable {

    private List<Observer> list;
    private String message;

    public WeChatServer(){
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        if (null != list){
            list.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if (null != list){
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

    public void setInfo(String message){
        this.message = message;
        System.out.println("微信服务更新消息----" + message);
        notifyObserver();
    }

}
