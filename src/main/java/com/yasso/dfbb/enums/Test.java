package com.yasso.dfbb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/10 14:53
 */
public class Test {

    public static void main(String[] args) {
       //check().get(0).forEach(str -> System.out.println(str));
        ii(Arrays.asList("23","24","25",29));
    }

    static List<List<String>> getStrings1() {
        String[] strings = {"q","w","e","r","t","y"};
        List<String> list = Arrays.asList(strings);
        List<List<String>> list1 = new ArrayList();
        list1.add(list);
        return list1;
    }

    static List<List<String>> check() {
        List<List<String>> strings1 = getStrings1();
        List<String> list = strings1.get(0);
        for (String s : list) {
            if (s.equals("q")) {
                list.set(list.indexOf(s),"qqq");
            }
        }
        return strings1;
    }


    static void ii(List<?> list) {
        list.forEach( s -> System.out.println(s));
    }

}
