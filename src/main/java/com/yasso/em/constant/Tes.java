package com.yasso.em.constant;

import java.util.Calendar;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/29 15:42
 */
public class Tes {
    public static void main(String[] args) {

//        Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();
//        System.out.println(c2.get(Calendar.YEAR));
//        System.out.println(c1.get(Calendar.YEAR));
//        System.out.println(c2.get(Calendar.MONTH));
//        int i = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
//        System.out.println(i);
        System.out.println(EMAFlowStatusEnum.COMMIT.status());
        System.out.println("\n\n");
        System.out.println(EMAFlowStatusEnum.toList());

    }
}
