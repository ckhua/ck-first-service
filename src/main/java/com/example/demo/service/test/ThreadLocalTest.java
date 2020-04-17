package com.example.demo.service.test;

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
        B b = new ThreadLocalTest().new B();
        B b2 = new ThreadLocalTest().new B();
        Thread t1 = new Thread() {

            @Override
            public void run() {
                b.method1();
            }

        };
        Thread t2 = new Thread() {

            @Override
            public void run() {
                b2.method1();
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("finish");
    }
}
