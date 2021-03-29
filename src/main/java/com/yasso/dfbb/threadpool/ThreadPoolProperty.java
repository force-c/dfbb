package com.yasso.dfbb.threadpool;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/3 10:43
 */
@Data
@Configuration
//@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolProperty {
    /**
     * 核心线程数
     */
    private int corePoolSize = 2;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 3;

    /**
     * 允许线程空闲时间 （单位：默认为秒）
     */
    private int keepAliveTime = 1;

    /**
     * 缓冲队列大小
     */
    private int queueCapacity = 200;

    /**
     * 线程名称前缀
     */
    private String threadNamePrefix;

    /**
     *
     */
    private boolean allowCoreThreadTimeOut = false;
}
