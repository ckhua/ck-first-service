package com.example.demo.service.query.impl;

import com.example.demo.VO.OrderCommonQueryParamVO;
import com.example.demo.VO.OrderCommonQueryVO;
import com.example.demo.service.query.context.OrderCommonQueryContext;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @Description 订单通用查询类
 * @Date 2020/4/15 14:43
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service(value = "ICommonQueryService_ORDER")
public class OrderCommonQueryServiceImpl extends AbstractCommonService<OrderCommonQueryContext, OrderCommonQueryParamVO, OrderCommonQueryVO> {

    @Override
    protected Function<OrderCommonQueryParamVO, OrderCommonQueryContext> buildContext() {
        return paramVO -> OrderCommonQueryContext.builder().build();
    }

    @Override
    protected Function<OrderCommonQueryContext, OrderCommonQueryVO> buildResult() {
        return context -> OrderCommonQueryVO.builder().orderNo("hello").build();
    }
}
