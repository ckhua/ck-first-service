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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @ApiOperation(value = "生产队列信息", notes = "生产队列信息")
    @PostMapping(value = "redisson/task/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean redissonTaskProduct(@ApiParam(value = "消费类型", required = true) @RequestParam(value = "dataConsumerType", required = true) RedissonDataConsumerType dataConsumerType) {

        //根据业务信息 获取对应服务信息
        String serviceName = Joiner.on("").join(ITaskDataProductService.class.getSimpleName(), "_", dataConsumerType.getCode());
        Optional.ofNullable(serviceName)
                .map(taskDataProductServiceMap::get)
                .ifPresent(service -> service.supply());
        return Boolean.TRUE;
    }

    private static Pattern headerPattern;
//    public static void main(String[] args) {
//        headerPattern = compile("^[swa/]+.*");
//        String path = "swa/to/showdoc";
//        Matcher m = headerPattern.matcher(path);
//        boolean matches = m.matches();
//        System.out.println(matches);
//
//        int count = m.groupCount();//返回2,因为有2组
//        System.out.println(count);
//
//
//        String group = m.group(1);
//        System.out.println(group);
//
//    }


    public static void main(String[] args) {

        // TODO Auto-generated method stub
        String str = "Hello,World! in Java.";
        Pattern pattern = Pattern.compile("W(or)(ld!)");
        Matcher matcher = pattern.matcher(str);
//        System.out.println(matcher.group(0));
        System.out.println(matcher.matches());
//        if (matcher.matches() && matcher.group(1) != null) {
//            //进来哦
//            System.out.println("正确");
//        }
        while (matcher.find()) {
            System.out.println("Group 0:" + matcher.group(0));//得到第0组——整个匹配
            System.out.println("Group 1:" + matcher.group(1));//得到第一组匹配——与(or)匹配的
            System.out.println("Group 2:" + matcher.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式
            System.out.println("Start 0:" + matcher.start(0) + " End 0:" + matcher.end(0));//总匹配的索引
            System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));//第一组匹配的索引
            System.out.println("Start 2:" + matcher.start(2) + " End 2:" + matcher.end(2));//第二组匹配的索引
            System.out.println(str.substring(matcher.start(0), matcher.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
        }
    }

}
