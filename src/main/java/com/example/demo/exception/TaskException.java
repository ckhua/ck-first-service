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
public enum TaskException implements ICkhEnumException {

    // 队列异常枚举
    TASK_CREATE_ERROR(1001, "队列创建异常"),
    INVALID_OPERATOR(1002, "无效的操作人"),
    TASK_BUSY(1003, "队列繁忙"),
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
