package com.example.demo.utils.common;

import java.util.Date;

/**
 * @Description 类注释
 * @Date 2020/8/3 14:16
 * @Author chen kang hua
 * @Version 1.0
 **/
public class Times {

    public static void test(String title, Task task) {
        if (task == null) {
            return;
        }
        System.out.println(title);
        System.out.println("开始  " + TimeUtils.formatDateTime(new Date(), TimeUtils.DEFAULT_TIME_PATTERN));
        long begin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("结束  " + TimeUtils.formatDateTime(new Date(), TimeUtils.DEFAULT_TIME_PATTERN));
        long elapsedTime = (end - begin) / 1000;
        System.out.println("耗时 ： " + elapsedTime + "秒");

    }

    private interface Task {
        void execute();
    }

    public static void main(String[] args) {
        int listSize = 36;
        int spitCount = 200;
        int skip = listSize % spitCount == 0 ? listSize / spitCount : listSize / spitCount + 1;
        for (int i = 0; i < skip; i++) {

            System.out.println(111);
        }
    }
}
