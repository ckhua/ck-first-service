package com.example.demo.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description 类注释
 * @Date 2020/4/17 16:12
 * @Author chen kang hua
 * @Version 1.0
 **/
@Configuration
public class NacosConfig {

    @Value("${server.port}")
    private int serverPort;
    @Value("${server.name}")
    private String applicationName;
    @NacosInjected
    private NamingService namingService;

    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "47.94.13.132", serverPort);
    }
}
