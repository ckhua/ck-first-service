##  基于 redis + lua 通过aop实现分布式限流 
- 位置 com.example.demo.aop.redis.limit.RedisRateLimiter
#### 实现原理
- 虚拟令牌： 假设设置的max为最大的存储令牌数
- 延迟计算： 当有请求来获取令牌时，计算当前时间与设置过期时间的令牌数，判断是否超过最大令牌数，没有超过则将生成的令牌加入令牌桶中。这样一来，只需要在获取令牌时计算一次即可。
- 使用lua脚本实现redis操作原子性
- 使用aop注解实现可拔插式接口限流

#### 流程图 idea无法显示 ，建议放markdown文档中显示一下

```
graph TB
A[获取限流注解]--> B{是否有该注解} 
B --> |否| C[结束] 
B --> |是| D[组装key] 
D --> E[清除redis过期的key]
E --> F[获取redis中该key的令牌数量]
F --> H{当前令牌数大于等于最大存储数量?} 
H --> |否| I[redis储存令牌并设置过期时间] 
H --> |是| J[抛出异常] 
I --> C
J --> C
``` 

    
#### 代码实现

- 限流注解接口
```
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
     * 标识时间段，默认1秒钟
     */
    long timeout() default 1;
    /**
     * 时间单位，默认 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
```

- 切面核心方法
```
//获取限流接口注解
RedisRateLimiter redisRateLimiter = AnnotationUtils.findAnnotation(method, RedisRateLimiter.class);
if (redisRateLimiter != null) {
    //是否触发限流  true 限流 false 通过
    boolean limited = shouldLimited(key, redisRateLimiter.max(), redisRateLimiter.timeout(), redisRateLimiter.timeUnit());
    //触发限流 抛出异常
    if (limited) {
        throw new GeneralException("手速太快了，慢点儿吧~");
    }
}
```

- lua 脚本
```
-- key值
local key = KEYS[1]
-- 获取参数
local now = tonumber(ARGV[1])
-- 当前key的过期时间
local newExpired = tonumber(ARGV[2])
local oldExpired = tonumber(ARGV[3])
local max = tonumber(ARGV[4])

-- 清除过期的数据 0-指定过期时间
redis.call('zremrangebyscore', key, 0, oldExpired)
-- 获取 zset 中的当前元素个数
local current = tonumber(redis.call('zcard', key))
local next = current + 1

if next > max then
  -- 达到限流大小 返回 0
  return 0;
else
  -- 往 zset 中添加一个值、得分均为当前时间戳的元素，[value,score]
  redis.call("zadd", key, now, now)
  -- 每次访问均重新设置 zset 的过期时间，单位毫秒
  redis.call("pexpire", key, newExpired)
  return next
end
```

- 实现限流时 redis 存储结构如下:
![tFiIrd.png](https://s1.ax1x.com/2020/05/26/tFiIrd.png)

