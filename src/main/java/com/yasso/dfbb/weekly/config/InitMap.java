package com.yasso.dfbb.weekly.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 周报自动处理功能 配置类
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 14:49
 */
@Component
public class InitMap {

    static Map<Integer,String> hashMap = new HashMap<>();
    static Map<String,Integer> hashMap2 = new HashMap<>();

    @PostConstruct
    public void initHashMap() {
        hashMap.put(1,"A");
        hashMap.put(2,"B");
        hashMap.put(3,"C");
        hashMap.put(4,"D");
        hashMap.put(5,"E");
        hashMap.put(6,"F");
        hashMap.put(7,"G");
        hashMap.put(8,"H");
        hashMap.put(9,"I");
        hashMap.put(10,"J");


        hashMap2.put("A",1);
        hashMap2.put("B",2);
        hashMap2.put("C",3);
        hashMap2.put("D",4);
        hashMap2.put("E",5);
        hashMap2.put("F",6);
        hashMap2.put("G",7);
        hashMap2.put("H",8);
        hashMap2.put("I",9);
        hashMap2.put("J",10);
    }

    public String getYAxis(Integer index) {
        return hashMap.get(index);
    }

    public Integer ByYAxis(String Letter) {
        return hashMap2.get(Letter);
    }
}
