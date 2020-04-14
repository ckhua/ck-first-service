package com.example.demo.controller;

import com.example.demo.model.WaysBuilder;
import com.example.demo.service.IMallQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 类注释
 * @Date 2020/4/14 11:28
 * @Author chen kang hua
 * @Version 1.0
 **/

@RestController
public class OrderQueryController {

    @Autowired
    private IMallQueryService mallQueryService;

    @GetMapping(value = "query/order" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  List<WaysBuilder> queryOrder(){
        List<WaysBuilder> waysMalls = mallQueryService.queryMallInfo();
        return waysMalls;
    };

}
