package com.example.demo.aop.rate.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Description 基于google RateLimiter限流切面
 * @Date 2020/5/13 10:53
 * @Author chen kang hua
 * @Version 1.0
 **/
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Aspect
public class AccessLimitAspect {


    private volatile Map<String, RateLimiter> rateHashMap = new ConcurrentHashMap<>();

    /**
     * AOP切入点
     */
    @Pointcut("@annotation(com.example.demo.aop.rate.limit.AccessLimit)")
    public void pointCut() {
    }

    /**
     * 切面方法
     *
     * @param point 切入点
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 1.如果请求方法上存在@ExtRateLimiter注解的话
        AccessLimit accessLimit = Optional.ofNullable(point)
                .map(this.getSignatureMethod()::apply)
                .map(method -> method.getDeclaredAnnotation(AccessLimit.class))
                .orElse(null);
        if (accessLimit == null) {
            return point.proceed();
        }
        double value = accessLimit.value();
        long timeout = accessLimit.timeout();
        System.out.println("每秒创建令牌数----------------------- value ： " + value);
        // 3.调用原生的RateLimiter创建令牌 保证每个请求对应都是单例的RateLimiter
        String requestURI = getRequestURI();
        if (rateHashMap.containsKey(requestURI)) {
            RateLimiter rateLimiter = rateHashMap.get(requestURI);
            System.out.println(rateLimiter);
        }
        RateLimiter limiter = Optional.ofNullable(requestURI)
                .filter(rateHashMap::containsKey)
                .map(uri -> rateHashMap.get(uri))
                .orElseGet(() -> {
                    // 如果在hashMap URL 没有检测到RateLimiter 添加新的RateLimiter
                    RateLimiter rateLimiter = null;
                    synchronized (AccessLimitAspect.class) {
                        System.out.println("创建锁---------------");
                        rateLimiter = RateLimiter.create(value);
                    }
                    rateHashMap.put(requestURI, rateLimiter);
                    return rateLimiter;
                });
        // 4.获取令牌桶中的令牌，如果没有有效期获取到令牌的话，则直接调用本地服务降级方法，不会进入到实际请求方法中。
        boolean tryAcquire = limiter.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            // 服务降级
            throw new RuntimeException("服务器繁忙!");
        }
        return point.proceed();
    }

    private String getRequestURI() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getRequestURI)
                .orElseThrow(() -> new RuntimeException("无效的请求url"));
    }

    // 获取到AOP拦截的方法
    private Function<ProceedingJoinPoint, Method> getSignatureMethod() {
        return point -> Optional.ofNullable(point.getSignature())
                .map(signature -> (MethodSignature) signature)
                .map(MethodSignature::getMethod)
                .orElseThrow(() -> new RuntimeException("无效的请求方法"));
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

}
