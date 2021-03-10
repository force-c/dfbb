package com.yasso.dfbb.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author guoc
 * @version 1.0
 * @date 2021/2/18 19:16
 */
public class Cat implements Observer {
    public String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        String prefix = o.toString();
        if (o instanceof Person) {
            prefix = ((Person) o).name;
        }
        System.out.println(prefix + "放" + arg + "le   " + name + "去吃鱼");
    }

    public static void main(String[] args) {
        Person person = new Person("郭闯");
        for (int i = 0; i < 10; i++) {
            person.addObserver(new Cat("cat" + i));
        }
        person.giveFish("鲸鱼");
    }

}
