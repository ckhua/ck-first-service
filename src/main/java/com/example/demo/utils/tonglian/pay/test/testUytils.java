package com.example.demo.utils.tonglian.pay.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.tonglian.pay.HttpConnectionUtil;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description 类注释
 * @Date 2021/6/1 14:32
 * @Author chen kang hua
 * @Version 1.0
 **/
public class testUytils {


    public static void main(String[] args) {
        try {
            System.out.println(pay());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String pay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil("https://vsp.allinpay.com/apiweb/gateway/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();


        String param = "{\n" +
                "\t\"appid\": \"00213105\",\n" +
                "\t\"charset\": \"utf-8\",\n" +
                "\t\"cusid\": \"56129004215KFBU\",\n" +
                "\t\"gateid\": \"0309\",\n" +
                "\t\"goodsid\": \"111\",\n" +
                "\t\"notifyurl\": \"http://qa-integrator.etowertech.com/api/shipper/v1/actPayment/call/call/allinPay/sBhBNM9EApQERIBxUZt9tw/97\",\n" +
                "\t\"orderid\": \"F7UFP98F2UD9FU\",\n" +
                "\t\"paytype\": \"032\",\n" +
                "\t\"randomstr\": \"92cbd95c-86ed-4e72-b01d-7b43e7d039b5\",\n" +
                "\t\"returl\": \"http://qa-integrator.etowertech.com/api/shipper/v1/actPayment/call/call/redirect?returnUrl=https://qa-shipper.etowertech.com/3c40304fc1c7404a9c0bfinance/rechargeRecord/recharge?status=1\",\n" +
                "\t\"sign\": \"5K6uyXXTYcJqvD5gBuCPxWWxdqNb7dy7JigSyezUBWRWPqdrqISV5Bp42/kskQygo8X+fzy9KQrAwR+WEbtEcw==\",\n" +
                "\t\"signtype\": \"SM2\",\n" +
                "\t\"trxamt\": \"11100\",\n" +
                "\t\"validtime\": \"15\"\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(param);
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();

        entries.forEach(entry -> {
            params.put(entry.getKey(), entry.getValue().toString());
        });
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
        return result;
    }
}
