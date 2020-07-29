package com.example.demo.service.query.impl;

import com.example.demo.service.query.context.OrderCommonQueryContext;
import com.example.demo.vo.DataCommonQueryVO;
import com.example.demo.vo.DataQueryParamVO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @Description 订单通用查询类
 * @Date 2020/4/15 14:43
 * @Author chen kang hua
 * @Version 1.0
 **/
@Service(value = "ICommonQueryService_ORDER")
public class OrderCommonQueryServiceImpl extends AbstractCommonService<OrderCommonQueryContext, DataQueryParamVO, DataCommonQueryVO> {

    @Override
    protected Function<DataQueryParamVO, OrderCommonQueryContext> buildContext() {
        return paramVO -> OrderCommonQueryContext.builder().build();
    }

    @Override
    protected Function<OrderCommonQueryContext, DataCommonQueryVO> buildResult() {
        return context -> DataCommonQueryVO.builder().orderNo("hello").build();
    }
}
