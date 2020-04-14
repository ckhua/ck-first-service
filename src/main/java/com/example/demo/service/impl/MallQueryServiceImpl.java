package com.example.demo.service.impl;

import com.example.demo.dal.WaysBuilderMapper;
import com.example.demo.model.WaysBuilder;
import com.example.demo.model.WaysBuilderExample;
import com.example.demo.service.IMallQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 类注释
 * @Date 2020/4/14 11:36
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service
public class MallQueryServiceImpl implements IMallQueryService {

    @Autowired
    private WaysBuilderMapper waysBuilderMapper;

    @Override
    public List<WaysBuilder> queryMallInfo() {

        WaysBuilderExample example = new WaysBuilderExample();
        example.createCriteria()
                .andBuilderIdIsNotNull();
        return this.waysBuilderMapper.selectByExample(example);
    }
}
