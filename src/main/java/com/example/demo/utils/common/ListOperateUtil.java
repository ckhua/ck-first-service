package com.example.demo.utils.common;

import com.example.demo.model.DataTaskRecord;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
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
public class ListOperateUtil {

    private static final Integer COUNT = 3;


    private static Function<List<String>, String> toJoin = list -> {
        String collect = list.stream().collect(Collectors.joining(","));
        return collect;
    };

    public static void main(String[] args) {
        List<String> objects = Lists.newArrayList();
        spitListAccept(objects, 4, System.out::println, (list) -> {
            String collect = ((List<String>) list).stream().collect(Collectors.joining(","));
            return collect;
        });
        List<DataTaskRecord> objects1 = Lists.newArrayList();
        spitListAccept(objects1, 1, System.out::println, null);

        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("5");
        objects.add("6");


        objects1.add(new DataTaskRecord(1));
        objects1.add(new DataTaskRecord(2));
        objects1.add(new DataTaskRecord(3));
        objects1.add(new DataTaskRecord(4));
        objects1.add(new DataTaskRecord(5));
        objects1.add(new DataTaskRecord(6));
        objects1.add(new DataTaskRecord(7));
        objects1.add(new DataTaskRecord(8));

    }


    /**
     * 切割集合执行方法
     *
     * @param sourceList
     * @param consumer
     * @param function
     * @param <T>
     */
    public static <T> void spitListAccept(List<T> sourceList, Integer spitCount, Consumer consumer, Function function) {

        if (CollectionUtils.isNotEmpty(sourceList)) {
            spitCount = Optional.ofNullable(spitCount)
                    .orElse(COUNT);
            Integer listSize = sourceList.size();
            // spitCount分为一批 默认 3组一批
            if (listSize > spitCount) {
                int skip = listSize % spitCount == 0 ? listSize / spitCount : listSize / spitCount + 1;
                int start = 0;
                int end = 0;
                for (int count = 1; count <= skip; count++) {
                    end = count * spitCount;
                    //组装目标集合
                    List<T> acceptList = Lists.newArrayList();
                    if (end >= listSize) {
                        acceptList = sourceList.subList(start, listSize);
                    } else {
                        acceptList = sourceList.subList(start, end);
                        start = end;
                    }
                    //消费目标集合
                    if (function != null) {
                        consumer.accept(function.apply(acceptList));
                    } else {
                        consumer.accept(acceptList);
                    }
                }
            } else {
                consumer.accept(sourceList);
            }
        }
    }

    private static Consumer<List<DataTaskRecord>> spList() {
        return context -> System.out.println("yd" + context);
    }


}
