package com.example.demo.exception;

import com.example.demo.enums.Prompt;

/**
 * CkhException
 *
 * @author chen kang hua
 * @description
 * @date 2019/11/28
 *  * @apiNote 继承自 {@link RuntimeException} 作为业务异常捕捉到。
 *  * -        通过调用 {@link CkhException#getEnum()} 来获取错误代码及错误信息
 */
public class CkhException extends RuntimeException {

    /**
     * 异常枚举接口
     */
    private ICkhEnumException waysEnum;

    /**
     * 默认构造方法，用 Private 修饰
     */
    private CkhException() {
        super();
    }

    /**
     * 构造方法
     *
     * @param waysEnum 实现异常枚举接口的枚举
     */
    public CkhException(ICkhEnumException waysEnum) {
        this();
        this.waysEnum = waysEnum;
    }

    /**
     * 构造方法
     *
     * @param waysEnum 实现异常枚举接口的枚举
     * @param t        原异常
     */
    public CkhException(ICkhEnumException waysEnum, Throwable t) {
        super(waysEnum.getMessage(), t);
        this.waysEnum = waysEnum;
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getCode() {
        return this.waysEnum.getCode();
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return this.waysEnum.getMessage();
    }

    /**
     * 打印错误栈
     *
     * @apiNote 在捕捉到异常时打印
     */
    public void print() {
        this.printStackTrace();
    }

    /**
     * 获取枚举接口信息
     *
     * @return 枚举接口信息
     * @apiNote 用于服务端向客户端描述异常时使用
     */
    public ICkhEnumException getEnum() {
        return waysEnum;
    }

    public static CkhException definedException(int code, String message, Prompt prompt) {
        return new CkhException(new ICkhEnumException() {
            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public Prompt getPrompt() {
                return prompt;
            }
        });
    }


}
