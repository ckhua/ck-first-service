package com.example.demo.aop.rate.limit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description rateLimit
 * @Date 2020/5/13 10:52
 * @Author chen kang hua
 * @Version 1.0
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    //标识 指定每秒发放的令牌数
    double value() default 5D;

    //标识 时间段 毫秒
    long timeout() default 500L;
}
