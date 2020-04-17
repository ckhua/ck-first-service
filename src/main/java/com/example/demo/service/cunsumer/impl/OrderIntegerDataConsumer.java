package com.example.demo.service.cunsumer.impl;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.service.cunsumer.AbstractIntegerDataConsumer;
import org.redisson.Redisson;
import org.springframework.stereotype.Component;

/**
 * @Description 业务消费类
 * @Date 2020/4/15 16:54
 * @Author chen kang hua
 * @Version 1.0
 **/
@Component
public class OrderIntegerDataConsumer extends AbstractIntegerDataConsumer {

    public OrderIntegerDataConsumer(Redisson redisson) {
        super(redisson);
    }

    @Override
    protected String businessType() {
        return RedissonDataConsumerType.ORDER.getCode();
    }
}
