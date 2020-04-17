package com.example.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
//@Import({Swagger2.class})
@NacosPropertySource(dataId = "ck-first-service", autoRefreshed = true)
@SpringBootApplication(scanBasePackages = {
        "com.example.demo",}
)
public class MySpringServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringServiceFirstApplication.class, args);
    }

}
