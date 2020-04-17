package com.example.demo.controller;

import com.example.demo.enums.RedissonDataConsumerType;
import com.example.demo.service.task.ITaskDataProductService;
import com.google.common.base.Joiner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @Description 阻塞队列提交
 * @Date 2020/4/14 11:28
 * @Author chen kang hua
 * @Version 1.0
 **/
@Api(tags = {"first-redisson阻塞队列"})
@RestController
public class TaskDataController {

    /**
     * 将 ITaskDataProductService 装配进入map
     */
    @Autowired
    private Map<String, ITaskDataProductService> taskDataProductServiceMap;


    @ApiOperation(value = "查询简单数据", notes = "查询简单数据")
    @PostMapping(value = "redisson/task/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean redissonTaskProduct(@ApiParam(value = "消费类型", required = true) @RequestParam(value = "dataConsumerType", required = true) RedissonDataConsumerType dataConsumerType) {

        //根据业务信息 获取对应服务信息
        String serviceName = Joiner.on("").join(ITaskDataProductService.class.getSimpleName(), "_", dataConsumerType.getCode());
        Optional.ofNullable(serviceName)
                .map(taskDataProductServiceMap::get)
                .ifPresent(service -> service.supply());
        return Boolean.TRUE;
    }

}
