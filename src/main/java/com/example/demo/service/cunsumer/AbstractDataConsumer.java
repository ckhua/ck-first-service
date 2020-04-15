package com.example.demo.service.cunsumer;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.DataTaskRecord;
import com.google.common.base.Joiner;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBoundedBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @Description redis 阻塞队列消费队列
 * @Date 2020/4/15 16:27
 * @Author chen kang hua
 * @Version 1.0
 **/
@Slf4j
public abstract class AbstractDataConsumer implements Consumer<DataTaskRecord> {

    @Autowired
    public AbstractDataConsumer(Redisson redisson) {
        // 基于 Redis 的分布式阻塞队列
        RBoundedBlockingQueue<DataTaskRecord> blockingQueue = redisson.getBoundedBlockingQueue("my:task-record:" + this.businessType());
        Try.of(() -> blockingQueue.trySetCapacity(32))
                .onFailure(t -> log.warn("尝试设置Redis队列长度失败.", t));
        blockingQueue.subscribeOnElements(this);
    }

    @Override
    public void accept(DataTaskRecord dataTaskRecord) {

        //执行业务信息
        String obj = Optional.ofNullable(dataTaskRecord)
                .map(JSONObject::toJSONString)
                .orElse("");

        System.out.println(Joiner.on(" : ").join("你好,队列已消费", "参数信息", obj));
    }


    /**
     * 根据业务定义类型
     *
     * @return 根据业务定义类型
     */
    protected abstract String businessType();
}
