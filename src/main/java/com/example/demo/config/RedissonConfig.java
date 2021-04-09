//package com.example.demo.config;
//
////@Configuration
//public class RedissonConfig {
//
////    @Value("${spring.redis.host}")
////    private String host;
////
////    @Value("${spring.redis.port}")
////    private String port;
////
////    //@Value("${spring.redis.password}")
////    private String password = "";
////
////    @Bean
////    public RedissonClient redissonClient() {
////        Config config = new Config();
////        config.useSingleServer().setAddress("redis://" + host + ":" + port);//.setPassword(password);
////
////        return Redisson.create(config);
////    }
//
//}
