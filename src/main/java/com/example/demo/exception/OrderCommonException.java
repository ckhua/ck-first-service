package com.example.demo.exception;

/**
 * @author chen kang hua
 * @version 1.0.0
 * @date 2019/7/16
 * @description 异常枚举类
 */
public enum OrderCommonException implements ICkhEnumException {

    QUERY_ORDER_DATA_EXCEPTION("查询订单信息异常", 41000001),

    ;

    private String message;
    private int code;


    OrderCommonException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
