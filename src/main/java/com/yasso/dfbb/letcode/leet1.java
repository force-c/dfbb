package com.yasso.dfbb.letcode;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/27 14:14
 */
public class leet1 {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(1^ 4));

        System.out.println(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));

        System.out.println(!(false || ((true || false))));

        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(format);

    }
}
