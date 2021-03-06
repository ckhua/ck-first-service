package com.example.demo.service.cunsumer.impl;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.service.cunsumer.AbstractDataConsumer;

/**
 * @Description 业务消费类
 * @Date 2020/4/15 16:54
 * @Author chen kang hua
 * @Version 1.0
 **/
public class OrderDataConsumer extends AbstractDataConsumer {


    public OrderDataConsumer() {
        super();
    }

    @Override
    protected String businessType() {
        return RedissonDataConsumerType.ORDER.getCode();
    }
}
