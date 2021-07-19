package com.example.demo.config.thread;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author crsit 2020/9/27 2:43 下午
 * @version 1.0.0
 */
@ApiModel("线程池参数配置")
@Data
@Configuration
public class ThreadPoolParamConfig {

    /**
     * 设置核心线程数,它是可以同时被执行的线程数量
     */
    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;
    /**
     * 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
     */
    @Value("${threadPool.maxPoolSize}")
    private int maxPoolSize;
    /**
     * 设置缓冲队列容量,在执行任务之前用于保存任务
     */
    @Value("${threadPool.queueCapacity}")
    private int queueCapacity;
    /**
     * 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
     */
    @Value("${threadPool.keepAliveSeconds}")
    private int keepAliveSeconds;
    /**
     * 等待所有任务结束后再关闭线程池
     */
    @Value("${threadPool.waitForTasksToCompleteOnShutdown}")
    private boolean waitForTasksToCompleteOnShutdown;
}
