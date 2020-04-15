package com.example.demo.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 阻塞队列消费类类型
 * @Date 2020/4/15 14:45
 * @Author chen kang hua
 * @Version 1.0
 **/
public enum RedissonDataConsumerType {

    ORDER("ORDER", "订单"),

    ;
    private String code;

    private String desc;


    RedissonDataConsumerType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<String, RedissonDataConsumerType> map;

    static {
        RedissonDataConsumerType[] values = RedissonDataConsumerType.values();
        map = Stream.of(values).collect(Collectors.toMap(RedissonDataConsumerType::getCode, e -> e));
    }

    public static RedissonDataConsumerType getByCode(String code) {
        return map.get(code);
    }

}
