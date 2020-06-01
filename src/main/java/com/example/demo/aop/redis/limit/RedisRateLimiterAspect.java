package com.example.demo.aop.redis.limit;

import cn.hutool.core.util.StrUtil;
import com.example.demo.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description redis lua 限流切面
 * @Date 2020/5/25 10:52
 * @Author chen kang hua
 * @Version 1.0
 **/
@Aspect
@Component
public class RedisRateLimiterAspect {

    private static final Logger log = LoggerFactory.getLogger(RedisRateLimiterAspect.class);

    private final static String SEPARATOR = ":";
    private final static String REDIS_LIMIT_KEY_PREFIX = "limit";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Long> limitRedisScript;

    @Pointcut("@annotation(com.example.demo.aop.redis.limit.RedisRateLimiter)")
    public void rateLimit() {
    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        log.info("限流 ------------------------------------------------------ start");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解
        RedisRateLimiter redisRateLimiter = AnnotationUtils.findAnnotation(method, RedisRateLimiter.class);
        if (redisRateLimiter != null) {
            String key = Optional.of(redisRateLimiter.key())
                    .filter(StringUtils::isNotBlank)
                    // 自定义key时  请求ip + 请求url + 类名 + 方法名 + 自定义key 做限流的key前缀
                    .map(value -> StrUtil.join(SEPARATOR, IpUtil.getIpAddress(getRequest()), getRequestURI(), method.getDeclaringClass().getName(), method.getName(), redisRateLimiter.key()))
                    // 默认用 请求url + 类名 + 方法名 做限流的 key 前缀
                    .orElse(StrUtil.join(SEPARATOR, getRequestURI(), method.getDeclaringClass().getName(), method.getName()));
            boolean limited = shouldLimited(key, redisRateLimiter.max(), redisRateLimiter.timeout(), redisRateLimiter.timeUnit());
            if (limited) {
                throw new RuntimeException("手速太快了，慢点儿吧~");
            }
        }
        log.info("限流 ------------------------------------------------------ end");
        return point.proceed();
    }

    private boolean shouldLimited(String key, long max, long timeout, TimeUnit timeUnit) {
        // 最终的 key 格式为：limit + key
        String finalKey = StrUtil.join(SEPARATOR, REDIS_LIMIT_KEY_PREFIX, key);
        // 统一使用单位毫秒
        long ttl = timeUnit.toMillis(timeout);
        // 当前时间毫秒数
        long now = Instant.now().toEpochMilli();
        // key的过期时间
        long expired = now - ttl;
        Long result = stringRedisTemplate.execute(limitRedisScript, Collections.singletonList(finalKey), now + "", ttl + "", expired + "", max + "");
        if (0 != result) {
            log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", finalKey, ttl, result);
            return Boolean.FALSE;
        } else {
            log.error("【{}】在单位时间 {} 毫秒内已达到访问上限，当前接口上限 {}", finalKey, ttl, max);
            return Boolean.TRUE;
        }
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private String getRequestURI() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getRequestURI)
                .orElseThrow(() -> new RuntimeException("无效的请求url"));
    }
}
