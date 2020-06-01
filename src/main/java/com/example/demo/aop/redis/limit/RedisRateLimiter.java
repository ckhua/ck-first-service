package com.example.demo.aop.redis.limit;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description redis lua 限流注解
 * @Date 2020/5/25 10:52
 * @Author chen kang hua
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisRateLimiter {
    long DEFAULT_REQUEST = 10;

    /**
     * max 最大请求数
     */
    @AliasFor("max") long value() default DEFAULT_REQUEST;

    /**
     * max 最大请求数
     */
    @AliasFor("value") long max() default DEFAULT_REQUEST;

    /**
     * 限流key
     */
    String key() default "";

    /**
     * 标识时间段，默认1秒
     */
    long timeout() default 1;

    /**
     * 时间单位，默认 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
