package com.example.demo.service.query.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Description 类注释
 * @Date 2020/4/15 14:51
 * @Author chen kang hua
 * @Version 1.0
 **/
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Accessors(chain = true)
public class OrderCommonQueryContext {

    @ApiModelProperty("商场ID")
    public String orderNo;
}
