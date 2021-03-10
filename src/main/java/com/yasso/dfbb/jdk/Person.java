package com.yasso.dfbb.jdk;

import java.util.Observable;

/**
 * @author guoc
 * @version 1.0
 * @date 2021/2/18 19:16
 */
public class Person extends Observable {

    public String name;
    public Person(String name) {
        this.name = name;
    }
    public void giveFish(String fishName) {
        setChanged();
        notifyObservers(fishName);
    }


}
