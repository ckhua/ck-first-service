package com.example.demo.utils.tonglian.pay.test;


import com.example.demo.utils.tonglian.pay.SybConstants;
import com.example.demo.utils.tonglian.pay.SybUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class NotifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NotifyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get method,no deal");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到通知");
        request.setCharacterEncoding("UTF-8");//通知传输的编码为GBK
        response.setCharacterEncoding("UTF-8");
        TreeMap<String, String> params = getParams(request);//动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容
        try {
            String appkey = "";
            if ("RSA".equals(params.get("signtype")))
                appkey = SybConstants.SYB_RSATLPUBKEY;
            else if ("SM2".equals(params.get("signtype")))
                appkey = SybConstants.SYB_SM2TLPUBKEY;
            else
                appkey = SybConstants.SYB_MD5_APPKEY;
            boolean isSign = SybUtil.validSign(params, appkey, params.get("signtype"));// 接受到推送通知,首先验签
            System.out.println("验签结果:" + isSign);
            //验签完毕进行业务处理
        } catch (Exception e) {//处理异常
            // TODO: handle exception
            e.printStackTrace();
        } finally {//收到通知,返回success
            response.getOutputStream().write("success".getBytes());
            response.flushBuffer();
        }
    }

    /**
     * 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容由于收银宝加字段而引起的签名异常
     *
     * @param request
     * @return
     */
    private TreeMap<String, String> getParams(HttpServletRequest request) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        Map reqMap = request.getParameterMap();
        for (Object key : reqMap.keySet()) {
            String value = ((String[]) reqMap.get(key))[0];
            System.out.println(key + ";" + value);
            map.put(key.toString(), value);
        }
        return map;
    }

}
