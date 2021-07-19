package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description 类注释
 * @Date 2020/10/13 11:55
 * @Author chen kang hua
 * @Version 1.0
 **/
public class ListUtils {


    public static <T> T tests(Supplier<T> s, Supplier<T> s1, Class<T> c) {
        T t = s.get();
        return t;
    }

    public static <T> List<T> filter(List<T> list, ListUtilsHook<T> hook) {
        ArrayList<T> r = new ArrayList<T>();
        for (T t : list) {
            if (hook.test(t)) {
                r.add(t);
            }
        }
        r.trimToSize();
        return r;
    }

    /**
     * 准备书的列表数据
     *
     * @return
     */
    public static List<AppModules> prepareData() {
        // 准备书的列表，id是从1到10
        List<AppModules> bookList = new ArrayList<AppModules>();
        for (int i = 1; i < 11; i++) {
            bookList.add(new AppModules("1", "book", i + 0L, "book"));
        }
        return bookList;
    }

    public static void main(String[] args) {


        String s = "测试";

        int length = s.length();
        String two = s.substring(0, 1);
        String first = s.substring(1, length);

        System.out.println(two + "   " + first);
    }

    public static String tst1() {
        return "";
    }

    ;

    public static String tst2() {
        return "";
    }

    ;

    public static String sss() {

        String s2 = tests(() -> tst2(), () -> tst2(), String.class);
        System.out.println();

        return s2;
    }


}
