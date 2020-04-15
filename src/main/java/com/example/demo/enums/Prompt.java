package com.example.demo.enums;

/**
 * @author kaijun.jiang
 * @version version 1.0  Exp.
 * @time 2018/6/15 上午11:00
 */
public enum Prompt {

    NORMAL("不提醒"),
    TOAST("短暂提示"),
    DIALOG("对话框");

    private String message;

    Prompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
