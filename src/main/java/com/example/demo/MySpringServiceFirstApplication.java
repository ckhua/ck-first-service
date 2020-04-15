package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
//@Import({Swagger2.class})
@SpringBootApplication(scanBasePackages = {
        "com.example.demo",}
)
public class MySpringServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringServiceFirstApplication.class, args);
    }

}
