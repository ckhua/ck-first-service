package com.example.demo.utils.func;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Description 类注释
 * @Date 2020/8/27 13:48
 * @Author chen kang hua
 * @Version 1.0
 **/
public class FuncTestUtil {

    public static void main(String[] args) {


        //测试 function
//        coverToString
//                .andThen(coverToLong)
//                .apply("1");
//
        //测试 consumer
//        consumerString
//                .andThen(consumerStringObj)
//                .accept("1");
//
        //测试 predicate
//        boolean test = predicateString
//                .and(predicateStringObj)
//                .test("");
//
        //测试 for 循环
//        List<Integer> integers = Lists.newArrayList(1);
//        fore(integers, (i) -> System.out.println(i));
//
        //测试 map 操作
//        List<DataTaskRecord> records = streamMap(integers, (s) -> new DataTaskRecord(s));
////        System.out.println(records);

        //测试 flatMap 操作
//        List< List<Object>> objects = Lists.newArrayList();
//        List<Object> s1 =  new ArrayList<>(1);
//        s1.add(11);
//        List<Object> s2 =  new ArrayList<>(1);
//        s2.add(22);
//        objects.add(s1);
//        objects.add(s2);
//        List<Object> objects1 = objects.stream()
//                .flatMap(s -> s.stream())
//                .collect(Collectors.toList());
//        System.out.println(objects1);
        //测试 分割工具类
//        List<String> objects = Lists.newArrayList();
//        objects.add("1");
//        objects.add("2");
//        objects.add("3");
//        objects.add("4");
//        objects.add("5");
//        objects.add("6");
//        ListOperateUtil.spitListAccept(objects, 4, (context) -> {
//            String collect = context.stream().collect(Collectors.joining(","));
//            System.out.println("s" + collect);
//        });
//
//        List<DataTaskRecord> objects1 = Lists.newArrayList();
//        objects1.add(new DataTaskRecord(1));
//        objects1.add(new DataTaskRecord(1));
//        objects1.add(new DataTaskRecord(3));
//        objects1.add(new DataTaskRecord(3));
//        objects1.add(new DataTaskRecord(5));
//        objects1.add(new DataTaskRecord(6));
//        objects1.add(new DataTaskRecord(7));
//        objects1.add(new DataTaskRecord(8));
//        ListOperateUtil.spitListAccept(objects1, 3, (c)-> {
//            List<Integer> integers = c.stream().map(DataTaskRecord::getRecordId).collect(Collectors.toList());
//        });

//        Map<Integer, String> stringMap = objects1.stream()
//                .collect(Collectors.groupingBy(DataTaskRecord::getRecordId, Collectors.collectingAndThen(Collectors.toList(), list -> {
//                    return list.stream().map(DataTaskRecord::getRecordId).map(String::valueOf).collect(Collectors.joining(","));
//                })));
//        System.out.println(stringMap);

        String str = "adCVsss111s1111";
        System.out.println(str);
        System.out.println(StringFilter(str));


    }

    //过滤特殊字符
    public static Boolean StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[\\da-zA-z]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.matches();
    }


}
