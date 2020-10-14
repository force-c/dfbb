package com.yasso.dfbb.designmode.observer;

public class to {

    public static void main(String[] args) {
        WeChatServer weChatServer = new WeChatServer();
        User tom = new User("tom");
        User jerry = new User("jerry");

        weChatServer.registerObserver(tom);
        weChatServer.registerObserver(jerry);

        weChatServer.setInfo("吃饭");
        System.out.println("==========微信服务推送消息===============");
        weChatServer.removeObserver(jerry);
        weChatServer.setInfo("喝水");
    }
}
