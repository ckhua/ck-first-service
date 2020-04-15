package com.example.demo.exception;

import com.example.demo.enums.Prompt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * ICkhEnumException
 *
 * @author chen kang hua
 * @description
 * @date 2019/11/28
 */
public interface ICkhEnumException extends Serializable {
    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    int getCode();

    /**
     * 获取消息类型
     *
     * @return
     */
    default Prompt getPrompt() {
        return Prompt.NORMAL;
    }

    /**
     * 获取是否隐私数据
     *
     * @return
     */
    default boolean getPrivacy() {
        return Boolean.FALSE;
    }

    /**
     * 输出枚举信息
     *
     * @param clazz 枚举类型
     */
    static <T extends ICkhEnumException> void output(Class<T> clazz) {
        output(clazz, "\t");
    }

    /**
     * 输出枚举信息
     *
     * @param clazz     枚举类型
     * @param separator 分隔符
     */
    static <T extends ICkhEnumException> void output(Class<T> clazz, CharSequence separator) {
        Optional.of(clazz)
                .filter(Enum.class::isAssignableFrom)
                .map(Class::getEnumConstants)
                .map(Arrays::stream)
                .map(stream -> stream
                        .map(enumException -> String.valueOf(enumException.getCode()) + separator + enumException.getMessage())
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("枚举信息输出异常"))
                .forEach(System.out::println);
    }

    /**
     * 业务异常 Supplier
     *
     * @return WaysException Supplier
     */
    default Supplier<CkhException> exception() {
        return () -> new CkhException(this);
    }
}
