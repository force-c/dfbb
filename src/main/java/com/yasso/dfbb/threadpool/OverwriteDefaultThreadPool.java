package com.yasso.dfbb.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 重写spring默认线程池
 * @author guochuang
 * @version 1.0
 * @date 2021/3/3 10:39
 */
//@EnableAsync
@Configuration
@RequiredArgsConstructor
public class OverwriteDefaultThreadPool implements AsyncConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OverwriteDefaultThreadPool.class);
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("default-pool-")
            .setUncaughtExceptionHandler((thread, throwable) -> LOGGER.error("ThreadPool {} got exception", thread, throwable))
            .build();
    private final ThreadPoolProperty threadPoolProperty;

    /**
     * 自定义线程池配置
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperty.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperty.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolProperty.getKeepAliveTime());
        executor.setQueueCapacity(threadPoolProperty.getQueueCapacity());
//        executor.setThreadNamePrefix(threadPoolProperty.getThreadNamePrefix());
        executor.setThreadFactory(threadFactory);
        executor.setAllowCoreThreadTimeOut(false);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 线程任务中的异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                LOGGER.info(method + throwable.getMessage());
            }
        };
    }
}
