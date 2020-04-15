package com.example.demo.controller;

import com.example.demo.VO.OrderCommonQueryParamVO;
import com.example.demo.VO.OrderCommonQueryVO;
import com.example.demo.enums.CommonQueryServiceType;
import com.example.demo.exception.OrderCommonException;
import com.example.demo.service.query.ICommonQueryService;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Description 数据查询类
 * @Date 2020/4/14 11:28
 * @Author chen kang hua
 * @Version 1.0
 **/
@Api(tags = {"first-数据查询"})
@RestController
public class OrderQueryController {

//    @Autowired
//    @Qualifier(value = "ICommonQueryService_ORDER")
//    private ICommonQueryService commonQueryService;

    /**
     * 将 ICommonQueryService 装配进入map
     */
    @Autowired
    private Map<String, ICommonQueryService> commonQueryServiceMap;


    @ApiOperation(value = "查询简单数据", notes = "查询简单数据")
    @PostMapping(value = "query/order/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<OrderCommonQueryVO> queryOrder(@ApiParam(value = "编号", required = false) @RequestParam(value = "recordId", required = false) Integer recordId,
                                               @ApiParam(value = "查询信息", required = false) @RequestBody OrderCommonQueryParamVO commonQueryParamVO) {

        //根据业务信息 获取对应服务信息
        String serviceName = Joiner.on("").join(ICommonQueryService.class.getSimpleName(), "_", CommonQueryServiceType.ORDER.getCode());
        commonQueryServiceMap.get(serviceName);
        ICommonQueryService queryService = Optional.ofNullable(serviceName)
                .map(commonQueryServiceMap::get)
                .orElseThrow(OrderCommonException.QUERY_ORDER_DATA_EXCEPTION.exception());

        //执行业务信息
        List<OrderCommonQueryVO> orderCommonQueryVOList = Optional.ofNullable(commonQueryParamVO)
                .map(vo -> queryService.queryData().apply(vo))
                .map(vo -> (OrderCommonQueryVO) vo)
                .map(Lists::newArrayList)
                .orElse(null);

        //真实业务需封装状态码
        return orderCommonQueryVOList;
    }

}
