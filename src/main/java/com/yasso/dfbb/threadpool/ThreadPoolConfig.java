package com.yasso.dfbb.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/2 12:07
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolConfig.class);

    /**
     * 核心线程数
     */
    private static final int corePoolSize = 8;

    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 50;

    /**
     * 允许线程空闲时间 （单位：默认为秒）
     */
    private static final int keepAliveTime = 10;

    /**
     * 缓冲队列大小
     */
    private static final int queueCapacity = 200;

    /**
     * 线程池前缀
     */
    private static final String threadNamePrefix = "Async-Service-";


    ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("custom-pool-%d")
            .setUncaughtExceptionHandler((thread, throwable) -> LOGGER.error("ThreadPool {} got exception", thread, throwable))
            .build();


    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
//        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setThreadFactory(threadFactory);
        // 任何线程空闲时间超过keepAliveTime后就会被销毁
        executor.setAllowCoreThreadTimeOut(true);
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean(name = "ThreadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                50,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardPolicy());
        return threadPoolExecutor;
    }

    public ForkJoinPool forkJoinPool() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return null;
    }





}
