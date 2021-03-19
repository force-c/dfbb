package com.yasso.test;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/11 17:13
 */
public class Tes1 {

    public static void main(String[] args) {
        Person fjc = new Person("fjc", 100);
        Person wxb = new Person("wxb", 100);
        Person fjc1 = new Person("fjc", 100);
        ArrayList<Person> people = new ArrayList<>();
        people.add(fjc);
        people.add(wxb);
        people.add(fjc1);
        Set<Person> personSet =  new TreeSet<>(Comparator.comparing(Person::getName));
        personSet.addAll(people);
        System.out.println(personSet.size());
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }

    @Data
    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }
    }

}
