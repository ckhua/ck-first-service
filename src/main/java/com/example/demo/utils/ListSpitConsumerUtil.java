package com.example.demo.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description list切割执行工具类
 * @Date 2020/8/11 18:19
 * @Author chen kang hua
 * @Version 1.0
 **/
@Slf4j
public class ListSpitConsumerUtil {

    private static final Integer COUNT = 3;
    private static Function<List<String>, String> yyy = list -> {
        String collect = list.stream().collect(Collectors.joining(","));
        return collect;
    };

    public static void main(String[] args) {
        List<String> objects = Lists.newArrayList();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("5");
        objects.add("6");
        objects.add("7");
        objects.add("8");
        spitListCount(objects, context -> System.out.println("s" + context), yyy);

        List<String> objects1 = Lists.newArrayList();
        objects1.add("1");
        objects1.add("2");
        objects1.add("3");
        objects1.add("4");
        objects1.add("5");
        objects1.add("6");
        objects1.add("7");
        objects1.add("8");
        spitListCount(objects1, spList(), null);
    }

    private static <T> void spitListCount(List<T> sourceList, Consumer consumer, Function function) {

        if (CollectionUtils.isNotEmpty(sourceList)) {
            Integer size = sourceList.size();
            // 300分为一批
            if (size > COUNT) {
                int skip = size % COUNT == 0 ? size / COUNT : size / COUNT + 1;
                int start = 0;
                int end = 0;
                for (int count = 1; count <= skip; count++) {
                    end = count * COUNT;
                    if (end >= size) {
                        List<T> endList = sourceList.subList(start, size);
                        if (function != null) {
                            consumer.accept(function.apply(endList));
                        } else {
                            consumer.accept(endList);
                        }
                    } else {
                        List<T> startList = sourceList.subList(start, end);
                        if (function != null) {
                            Object apply = function.apply(startList);
                            consumer.accept(apply);
                        } else {
                            consumer.accept(startList);
                        }
                        start = end;
                    }
                }
            } else {
                consumer.accept(sourceList);
            }
        }
    }

    private static Consumer<List<Long>> spList() {
        return context -> {
            System.out.println("yd" + context);
        };
    }


}
