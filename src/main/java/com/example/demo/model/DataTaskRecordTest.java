package com.example.demo.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 类注释
 * @Date 2020/4/15 16:29
 * @Author chen kang hua
 * @Version 1.0
 **/
@Builder(builderClassName = "Builder", toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Accessors(chain = true)
public class DataTaskRecordTest implements Serializable {

    private Integer recordId;

    private Boolean isBo;
}
