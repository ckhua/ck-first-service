package com.example.demo.service.task.impl;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.exception.CkhException;
import com.example.demo.exception.TaskException;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description 业务处理服务
 * @Date 2020/4/15 17:14
 * @Author chen kang hua
 * @Version 1.0
 **/
@Slf4j
@Service(value = "ITaskDataProductService_ORDER")
@Validated
public class DataTaskProductServiceImpl extends AbstractTaskDataProductService {

    /**
     * Redisson
     */
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void supply() {

        //获取阻塞队列信息
        RBoundedBlockingQueue<String> blockingQueue = this.redissonClient.getBoundedBlockingQueue("my:task-record:string:" + this.getDataType());
        //根据需要数据进行处理
        //将数据放入队列
        Try.of(() -> blockingQueue.putAsync(this.getTaskRecord()))
                .mapTry(future -> future.get(5, TimeUnit.SECONDS))
                .recoverWith(TimeoutException.class, Try.failure(new CkhException(TaskException.TASK_BUSY)))
                .recoverWith(CancellationException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .recoverWith(InterruptedException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .recoverWith(ExecutionException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .get();
    }

    @Override
    protected String getDataType() {
        return RedissonDataConsumerType.ORDER.getCode();
    }

    @Override
    protected String getTaskRecord() {
        return "hello";
    }

}
