-- key值
local key = KEYS[1]

-- 获取参数
local now = tonumber(ARGV[1])
local cacheTime = tonumber(ARGV[2])
local expired = tonumber(ARGV[3])
local max = tonumber(ARGV[4])

-- 清除过期的数据 0-指定过期时间
redis.call('zremrangebyscore', key, 0, expired)

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
  redis.call("pexpire", key, cacheTime)
  return next
end
