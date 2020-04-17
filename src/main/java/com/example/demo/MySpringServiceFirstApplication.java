package com.example.demo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Import({Swagger2.class})
@NacosPropertySource(dataId = "study", autoRefreshed = true)
@SpringBootApplication(scanBasePackages = {
        "com.example.demo",}
)
@RestController
public class MySpringServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringServiceFirstApplication.class, args);
    }

    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
    private String serverName;

    @GetMapping(value = "/test")
    @ResponseBody
    public String get() {
        return serverName;
    }

}
