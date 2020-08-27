package com.example.demo.utils.func;

import com.example.demo.model.DataTaskRecord;
import com.example.demo.utils.common.ListOperateUtil;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description 类注释
 * @Date 2020/8/24 17:29
 * @Author chen kang hua
 * @Version 1.0
 **/
public class FuncUtil {


    static Function<String, String> coverToString = s -> "";
    static Function<String, Long> coverToLong = s -> 1L;
    static Consumer<String> consumerString = obj -> {
    };
    static Consumer<String> consumerStringObj = obj -> {
    };
    static Predicate<String> predicateString = obj -> true;
    static Predicate<String> predicateStringObj = obj -> Boolean.TRUE;
    private static Consumer<String> ss = s -> {
        System.out.println(s);
    };
    private static Function<List<String>, String> toJoin = list -> {
        String collect = list.stream().collect(Collectors.joining(","));
        return collect;
    };

    public static void main(String[] args) {

        coverToString
                .andThen(coverToLong)
                .apply("1");

        consumerString
                .andThen(consumerStringObj)
                .accept("1");

        boolean test = predicateString
                .and(predicateStringObj)
                .test("");


        List<Integer> integers = Lists.newArrayList(1);
        fore(integers, (i) -> System.out.println(i));


        List<DataTaskRecord> records = streamMap(integers, (s) -> new DataTaskRecord(s));
        System.out.println(records);

        List<String> objects = Lists.newArrayList();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("5");
        objects.add("6");
        ListOperateUtil.spitListAccept(objects, 4, context -> System.out.println("s" + context), toJoin);

        List<DataTaskRecord> objects1 = Lists.newArrayList();
        objects1.add(new DataTaskRecord(1));
        objects1.add(new DataTaskRecord(1));
        objects1.add(new DataTaskRecord(3));
        objects1.add(new DataTaskRecord(3));
        objects1.add(new DataTaskRecord(5));
        objects1.add(new DataTaskRecord(6));
        objects1.add(new DataTaskRecord(7));
        objects1.add(new DataTaskRecord(8));
        ListOperateUtil.spitListAccept(objects1, 3, spList(), null);
        Map<Integer, String> stringMap = objects1.stream()
                .collect(Collectors.groupingBy(DataTaskRecord::getRecordId, Collectors.collectingAndThen(Collectors.toList(), list -> {
                    return list.stream().map(DataTaskRecord::getRecordId).map(String::valueOf).collect(Collectors.joining(","));
                })));
        System.out.println(stringMap);
    }

    /**
     * for 循环
     *
     * @param list
     * @param consumer
     * @param <T>
     */
    private static <T> void fore(List<T> list, Consumer consumer) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            consumer.accept(list.get(i));
        }
    }

    private static Consumer<String> ss() {
        return (s) -> System.out.println(s);
    }

    /**
     * map
     *
     * @param list
     * @param <T>
     */
    private static <T, R> List<R> streamMap(List<T> list, Function<T, R> function) {
        int size = list.size();
        List<R> list1 = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            R apply = function.apply(list.get(i));
            list1.add(apply);
        }
        return list1;
    }

    private static Consumer<List<DataTaskRecord>> spList() {
        return context -> System.out.println("yd" + context);
    }
}
