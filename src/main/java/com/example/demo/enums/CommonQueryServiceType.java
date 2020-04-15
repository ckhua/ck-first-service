package com.example.demo.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 类注释
 * @Date 2020/4/15 14:45
 * @Author chen kang hua
 * @Version 1.0
 **/
public enum CommonQueryServiceType {

    ORDER("ORDER", "订单"),

    ;
    private String code;

    private String desc;


    CommonQueryServiceType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<String, CommonQueryServiceType> map;

    static {
        CommonQueryServiceType[] values = CommonQueryServiceType.values();
        map = Stream.of(values).collect(Collectors.toMap(CommonQueryServiceType::getCode, e -> e));
    }

    public static CommonQueryServiceType getByCode(String code) {
        return map.get(code);
    }

}
