package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 类注释
 * @Date 2020/10/13 11:55
 * @Author chen kang hua
 * @Version 1.0
 **/
public class ListUtils {

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


        String s = "";
        Boolean s1 = Boolean.FALSE;

        System.out.println(s + "   " + s1);
    }

    public static String sss(String s, Boolean s1) {

        s1 = Boolean.TRUE;
        return "2222";
    }


}
