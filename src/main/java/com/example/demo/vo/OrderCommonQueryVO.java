package com.example.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 数据查询类
 * @Date 2020/4/15 14:59
 * @Author chen kang hua
 * @Version 1.0
 **/
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Accessors(chain = true)
public class OrderCommonQueryVO implements Serializable {

    @ApiModelProperty("订单号")
    public String orderNo;

}
