package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dal")
public class MySpringServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringServiceFirstApplication.class, args);
    }

}
