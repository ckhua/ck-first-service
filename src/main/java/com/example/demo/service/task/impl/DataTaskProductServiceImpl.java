package com.example.demo.service.task.impl;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.exception.CkhException;
import com.example.demo.exception.TaskException;
import com.example.demo.model.DataTaskRecord;
import io.vavr.control.Try;
import org.redisson.Redisson;
import org.redisson.api.RBoundedBlockingQueue;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description 类注释
 * @Date 2020/4/15 17:14
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service(value = "ITaskDataProductService_ORDER")
@Validated
public class DataTaskProductServiceImpl extends AbstractTaskDataProductService {

    /**
     * Redisson
     */
    private Redisson redisson;

    public DataTaskProductServiceImpl(Redisson redisson){
        this.redisson = redisson;
    }

    @Override
    public void supply(){
        RBoundedBlockingQueue<DataTaskRecord> blockingQueue = this.redisson.getBoundedBlockingQueue("my:task-record:" + this.getDataType());
        //根据需要数据进行处理
        Try.of(() -> blockingQueue.putAsync(this.getTaskRecord()))
                .mapTry(future -> future.get(5, TimeUnit.SECONDS))
                .recoverWith(TimeoutException.class, Try.failure(new CkhException(TaskException.TASK_BUSY)))
                .recoverWith(CancellationException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .recoverWith(InterruptedException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .recoverWith(ExecutionException.class, Try.failure(new CkhException(TaskException.TASK_CREATE_ERROR)))
                .get();
    }

    @Override
    protected String getDataType(){
        return RedissonDataConsumerType.ORDER.getCode();
    }

    @Override
    protected DataTaskRecord getTaskRecord(){
        return DataTaskRecord.builder().recordId(666).build();
    }

}
