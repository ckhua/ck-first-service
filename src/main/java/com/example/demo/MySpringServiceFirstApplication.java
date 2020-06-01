package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
//@NacosPropertySource(dataId = "ck-first-service", autoRefreshed = true)
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {
        "com.example.demo",}
)
public class MySpringServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringServiceFirstApplication.class, args);
    }

}
