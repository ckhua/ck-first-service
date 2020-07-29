package com.example.demo.controller;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description 算法测试类
 * @Date 2020/7/7 14:34
 * @Author chen kang hua
 * @Version 1.0
 **/
public class ArithmeticTestController {

    public static void main(String[] args) {
        List<List<String>> objects = Lists.newArrayList();
        int[] ints = {5864, -12333, 4151, -6239, -10306, -2544, -129};
        bubbleSort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        isSub("acb", "ahbgdc");
    }

    private static Boolean isSub(String s, String r) {
        for (char f : s.toCharArray()) {
            int i = r.indexOf(f);
            if (i == -1) {
                System.out.println("-----不存在");
                return false;
            }
        }
        return true;
    }


    /**
     * 冒泡排序
     *
     * @return
     */
    public static int[] bubbleSort(int[] sortArray) {

        for (int i = 0; i < sortArray.length - 1; i++) {
            for (int j = i + 1; j < sortArray.length; j++) {
                if (sortArray[i] < sortArray[j]) {
                    int tmp = sortArray[i];
                    sortArray[i] = sortArray[j];
                    sortArray[j] = tmp;
                }
            }
        }

        return sortArray;
    }


    /**
     * 斐波那契数列
     */

    public static int feiBo(int n) {

        if (n <= 2) {
            return n;
        }
        int n1 = 1;
        int n2 = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }

        return sum;
    }


}
