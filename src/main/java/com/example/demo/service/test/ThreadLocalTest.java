package com.example.demo.service.test;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.RandomUtils;

/**
 * @Description 测试线程是否同一把锁
 * @Date 2020/4/17 13:51
 * @Author chen kang hua
 * @Version 1.0
 **/
public class ThreadLocalTest {

    A a = new A();
    static C c = new C();

    class A {
        public void method1() {
            System.out.println("ThreadTest.A.method1()");
        }
    }

    static class C {

    }

    class B {
        public void method1() {
            synchronized ("111") { // 直接进行加锁
                int t = RandomUtils.nextInt(3000, 5000);
                System.out.println(" b1 sleep begin " + t);
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                }
                a.method1();
                System.out.println(" b1 sleep end " + t);
            }

            synchronized (new String("111")) { // 直接进行加锁
                int t = RandomUtils.nextInt(3000, 5000);
                System.out.println("b2 sleep begin " + t);
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                }
                a.method1();
                System.out.println("b2 sleep end " + t);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        String s1 = "a,B,c";

        String s2 = "a,b,c";
        System.out.println(StrUtil.equalsIgnoreCase(s1, s2));
        System.out.println(StrUtil.equals(s1, s2));

        ;

    }
}
