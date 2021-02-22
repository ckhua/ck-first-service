package com.example.demo.utils.thread.common;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @Description list切割执行工具类
 * @Date 2020/8/11 18:19
 * @Author chen kang hua
 * @Version 1.0
 **/
@Slf4j
public class ListOperateUtil {

    private static final Integer COUNT = 3;

    /**
     * 切割集合执行方法
     *
     * @param sourceList   目标集合
     * @param spitCount    切割数量
     * @param listConsumer 执行消费函数
     * @param <T>
     */
    public static <T> void spitListAccept(List<T> sourceList, Integer spitCount,
        Consumer<List<T>> listConsumer) {

        if (CollectionUtils.isNotEmpty(sourceList)) {
            spitCount = Optional.ofNullable(spitCount)
                .orElse(COUNT);
            Integer listSize = sourceList.size();
            // spitCount分为一批 默认 3组一批
            if (listSize > spitCount) {
                int skip =
                    listSize % spitCount == 0 ? listSize / spitCount : listSize / spitCount + 1;
                int start = 0;
                int end = 0;
                for (int count = 1; count <= skip; count++) {
                    end = count * spitCount;
                    //组装目标集合
                    List<T> acceptList;
                    if (end >= listSize) {
                        acceptList = sourceList.subList(start, listSize);
                    } else {
                        acceptList = sourceList.subList(start, end);
                        start = end;
                    }
                    //消费目标集合
                    listConsumer.accept(acceptList);
                }
            } else {
                listConsumer.accept(sourceList);
            }
        }
    }

    public static void main(String[] args) {
        List<String> objects = Lists.newArrayList();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("14");

        spitListAccept(objects, 3, (context) -> {
            context.forEach(System.out::print);
            System.out.println("--");
        });


    }
}
