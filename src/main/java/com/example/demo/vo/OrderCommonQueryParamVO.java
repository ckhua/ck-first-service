package com.example.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 数据查询参数
 * @Date 2020/4/15 14:59
 * @Author chen kang hua
 * @Version 1.0
 **/
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Accessors(chain = true)
public class OrderCommonQueryParamVO implements Serializable {

    @ApiModelProperty("商场ID")
    public String orderNo;

}
