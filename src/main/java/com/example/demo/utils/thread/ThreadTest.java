package com.example.demo.utils.thread;

import com.example.demo.utils.common.TimeUtils;
import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description 类注释
 * @Date 2020/9/30 15:34
 * @Author chen kang hua
 * @Version 1.0
 **/
public class ThreadTest implements Runnable {

    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    static int i = 0;
    private volatile static String IP_SUFFIX = null;

    public static synchronized void add() {
        i++;
    }

    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0, 8000).parallel().forEach(i -> {
            orderNos.add(generateOrderNo());
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("订单样例：" + orderNos.get(22));
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤重复后订单数：" + filterOrderNos.size());
        System.out.println("重复订单数：" + (orderNos.size() - filterOrderNos.size()));
    }

    public static String generateOrderNo() {
        String dateTime = TimeUtils.formatLocalDateTime(LocalDateTime.now(), TimeUtils.DEFAULT_TIME_PATTERN_FD);
        if (SEQ.intValue() > 9990) {
            SEQ.getAndSet(1000);
        }
        return dateTime + getLocalIpSuffix() + SEQ.getAndIncrement();
    }

    private static String getLocalIpSuffix() {
        if (null != IP_SUFFIX) {
            return IP_SUFFIX;
        }
        try {
            synchronized (ThreadTest.class) {
                if (null != IP_SUFFIX) {
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                //  172.17.0.4  172.17.0.199 ,
                String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
                return IP_SUFFIX;
            }
        } catch (Exception e) {
            System.out.println("获取IP失败:" + e.getMessage());
            IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
            return IP_SUFFIX;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            add();
        }
    }


}
