package com.example.demo.service.query;

import java.util.function.Function;

/**
 * @Description 查询数据类抽象服务
 * @Date 2020/4/15 14:36
 * @Author chen kang hua
 * @Version 1.0
 **/
public interface ICommonQueryService<Param, Result> {

    /**
     * 通用查询数据接口
     * @return
     */
    Function<Param, Result> queryData();
}
