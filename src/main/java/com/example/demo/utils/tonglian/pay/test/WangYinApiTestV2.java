package com.example.demo.utils.tonglian.pay.test;


import com.example.demo.utils.tonglian.pay.SybGatewayDemo;

import java.util.Map;
import java.util.UUID;


public class WangYinApiTestV2 {
    public static void main(String[] args) throws Exception {
//		testCancel();//撤销
//		testRefund();//退款
//		String testPay = testPay();
//		System.out.println(testPay);
        testQuery();//查询
    }

    public static void testCancel() throws Exception {
        // TODO Auto-generated method stub
        String reqsn = String.valueOf(System.currentTimeMillis());
        Map<String, String> map = SybGatewayDemo.cancel(1, "", "112094120001239214");
        print(map);
    }

    public static void testQuery() throws Exception {
        SybGatewayDemo service = new SybGatewayDemo();
        Map<String, String> map = SybGatewayDemo.query("", "122118010000398857");
        print(map);
    }

    public static void testRefund() throws Exception {
        String reqsn = String.valueOf(System.currentTimeMillis());
        Map<String, String> map = SybGatewayDemo.refund(1, reqsn, "112094120001239214");
        print(map);
    }

    public static String testPay() throws Exception {
        String reqsn = String.valueOf(System.currentTimeMillis());
        String s = SybGatewayDemo.pay(UUID.randomUUID().toString().replaceAll("-", ""), 1000L, "0306", "B/B", "2222", "http://chenkh.ticp.vip/v1/actPayment/call/call/allinPay");
        return s;
    }


    public static void print(Map<String, String> map) {
        System.out.println("返回数据如下:");
        if (map != null) {
            for (String key : map.keySet()) {
                System.out.println(key + ";" + map.get(key));
            }
        }
    }


}
