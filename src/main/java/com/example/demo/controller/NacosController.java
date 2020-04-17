package com.example.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description 类注释
 * @Date 2020/4/17 16:14
 * @Author chen kang hua
 * @Version 1.0
 **/
public class NacosController {

    @NacosInjected
    private NamingService namingService;

    @NacosValue(value = "${service.name:null}", autoRefreshed = true)
    private String myName;

    @ApiOperation(value="查询配置信息")
    @GetMapping(value = "/info")
    public String info () {
        return myName;
    }


    @ApiOperation(value="查询服务列表")
    @GetMapping(value = "/getServerList")
    public List<Instance> getServerList (@RequestParam String serviceName) {
        try {
            return namingService.getAllInstances(serviceName) ;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
