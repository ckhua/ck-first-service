package com.example.demo.enums;

/**
 * @Description 关键字配置类型
 * @Date 2020/6/9 14:45
 * @Author chen kang hua
 * @Version 1.0
 **/
public enum WordsTypeEnum {

    DEFAULT("DEFAULT", "默认"),

    ;

    private String type;

    private String name;

    WordsTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
