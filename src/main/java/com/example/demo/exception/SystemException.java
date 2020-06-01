package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TaskException
 * 队列异常枚举
 * @author chen kang hua
 * @description
 * @date 2019/11/28
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum SystemException implements ICkhEnumException {

    // 服务器繁忙
    SERVER_BUSY(0001, "服务器繁忙!!!"),
    ;

    /**
     * 错误代码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;
}
