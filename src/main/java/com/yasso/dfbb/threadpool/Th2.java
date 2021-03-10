package com.yasso.dfbb.threadpool;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/2 12:23
 */
@Service
@RequiredArgsConstructor
public class Th2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Th2.class);

    private final ThreadPoolTaskExecutor executor;

    private Integer nums = 2;

    public void th2_111() {
        executor.submit(() -> {
            LOGGER.info("nice");
        });
        return;
    }


    @Async("taskExecutor")
    public void th2_1() {
        for (Integer i = 0; i < nums; i++) {
            LOGGER.info("1");
        }
    }

    @Async
    public void th2_2() {
        for (Integer i = 0; i < nums; i++) {
            LOGGER.info("2");
//            throw new RuntimeException("opopop");
        }
    }

    @Async
    public void th2_3() {
        executor.execute(() -> {
            for (Integer i = 0; i < nums; i++) {
                LOGGER.info("3");
            }
        });
    }

    @Async
    public void th2_4() {
        executor.execute(() -> {
            for (Integer i = 0; i < nums; i++) {
                LOGGER.info("4");
            }
        });
    }

    public void th2_5() {
        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "succ";
            }
        };
        Future<String> future = executor.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void th2_6() {
        FutureTask<String> futureTask = new FutureTask<>(() -> "执行中");
        executor.submit(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private final AtomicBoolean isRunning = new AtomicBoolean(true);

    public void th2_7() {
        List<Integer> list = new LinkedList<>();
        long startTime = System.currentTimeMillis();
        int ll = 0;
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            new  Thread(() -> {
                list.add(finalI);
                System.out.println(Thread.currentThread().getName());
            });
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "    time7");
    }


    public void th2_8() {
        List<Integer> list = new LinkedList<>();
        long startTime = System.currentTimeMillis();
        AtomicInteger kk = new AtomicInteger(20);
        for (int i = 0; i < 100; i++) {
            //                synchronized (this) {
            //                    if (kk.get() >= 10) {
            //                        kk.getAndDecrement();
            //                    }
            //                }
            executor.submit(this::count);

        }
        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime + "    time8");
    }
    private Integer ki = 20;
    private synchronized void count() {
        if (ki > 0){
            System.out.println(--ki);
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(0);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ExecutorService executorService3 = Executors.newWorkStealingPool(9);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        scheduledThreadPool.scheduleWithFixedDelay(() -> {

        }, 10, 10, TimeUnit.SECONDS);
    }


}
