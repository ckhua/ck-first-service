package com.example.demo.config.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class ThreadPoolConfig {

    public final ThreadPoolParamConfig threadPoolParamConfig;

    private ThreadPoolTaskExecutor getDefaultThreadPoolTaskExecutor(String poolNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,它是可以同时被执行的线程数量
        executor.setCorePoolSize(threadPoolParamConfig.getCorePoolSize());
        // 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
        executor.setMaxPoolSize(threadPoolParamConfig.getMaxPoolSize());
        // 设置缓冲队列容量,在执行任务之前用于保存任务
        executor.setQueueCapacity(threadPoolParamConfig.getQueueCapacity());
        // 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
        executor.setKeepAliveSeconds(threadPoolParamConfig.getKeepAliveSeconds());
        // 设置线程名称前缀
        executor.setThreadNamePrefix(poolNamePrefix);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(
                threadPoolParamConfig.isWaitForTasksToCompleteOnShutdown());
        //初始化
        executor.initialize();
        return executor;
    }

    /**
     * 导入商家数据线程池
     *
     * @return
     */
    @Bean(name = "asyncBuyOrderThread")
    public ThreadPoolTaskExecutor asyncSnapshotThread() {
        return getDefaultThreadPoolTaskExecutor("asyncBuyOrderThread-");
    }

}
