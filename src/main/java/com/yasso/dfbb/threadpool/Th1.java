package com.yasso.dfbb.threadpool;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/2 12:23
 */
@Component
@RequiredArgsConstructor
public class Th1 {

    private final Th2 th2;

    @PostConstruct
    public void th1_1() {
        th2.th2_111();
        th2.th2_2();
        th2.th2_3();
        th2.th2_4();
    }
}
