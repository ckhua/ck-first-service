package com.example.demo.utils.tonglian.pay;


import java.util.Map;
import java.util.TreeMap;


public class SybGatewayDemo {
    public static Map<String, String> cancel(long trxamt, String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.HOSTNAME + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("signtype", SybConstants.SIGN_TYPE);
        String appkey = "";
        if (SybConstants.SIGN_TYPE.equals("RSA"))
            appkey = SybConstants.SYB_RSACUSPRIKEY;
        else if (SybConstants.SIGN_TYPE.equals("SM2"))
            appkey = SybConstants.SYB_SM2PPRIVATEKEY;
        else
            appkey = SybConstants.SYB_MD5_APPKEY;
        params.put("sign", SybUtil.unionSign(params, appkey, SybConstants.SIGN_TYPE));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    public static Map<String, String> refund(long trxamt, String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.HOSTNAME + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("trxamt", String.valueOf(trxamt));
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("signtype", SybConstants.SIGN_TYPE);
        String appkey = "";
        if (SybConstants.SIGN_TYPE.equals("RSA"))
            appkey = SybConstants.SYB_RSACUSPRIKEY;
        else if (SybConstants.SIGN_TYPE.equals("SM2"))
            appkey = SybConstants.SYB_SM2PPRIVATEKEY;
        else
            appkey = SybConstants.SYB_MD5_APPKEY;
        params.put("sign", SybUtil.unionSign(params, appkey, SybConstants.SIGN_TYPE));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    public static Map<String, String> query(String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.HOSTNAME + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("signtype", SybConstants.SIGN_TYPE);
        String appkey = "";
        if (SybConstants.SIGN_TYPE.equals("RSA"))
            appkey = SybConstants.SYB_RSACUSPRIKEY;
        else if (SybConstants.SIGN_TYPE.equals("SM2"))
            appkey = SybConstants.SYB_SM2PPRIVATEKEY;
        else
            appkey = SybConstants.SYB_MD5_APPKEY;
        params.put("sign", SybUtil.unionSign(params, appkey, SybConstants.SIGN_TYPE));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }


    /**
     * @param trxamt
     * @return
     * @throws Exception
     */
    public static String pay(String orderid, long trxamt, String gateid, String paytype, String remark, String notify_url) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.HOSTNAME + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("charset", "UTF-8");
        params.put("trxamt", String.valueOf(trxamt));
        //交易银行
        params.put("gateid", gateid);
        params.put("paytype", paytype);
        params.put("randomstr", SybUtil.getValidatecode(8));
        params.put("orderid", orderid);
        params.put("goodsinf", remark);
        params.put("returl", "http://chenkh.ticp.vip/test/pay/2");
        params.put("notifyurl", notify_url);
        params.put("signtype", SybConstants.SIGN_TYPE);
        String appkey = "";
        if (SybConstants.SIGN_TYPE.equals("RSA"))
            appkey = SybConstants.SYB_RSACUSPRIKEY;
        else if (SybConstants.SIGN_TYPE.equals("SM2"))
            appkey = SybConstants.SYB_SM2PPRIVATEKEY;
        else
            appkey = SybConstants.SYB_MD5_APPKEY;
        params.put("sign", SybUtil.unionSign(params, appkey, SybConstants.SIGN_TYPE));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        return result;

    }

    private static Map<String, String> handleResult(String result) throws Exception {
        System.out.println("ret:" + result);
        Map map = SybUtil.json2Obj(result, Map.class);
        if (map == null) {
            throw new Exception("返回数据错误");
        }
        if ("SUCCESS".equals(map.get("retcode"))) {
            TreeMap tmap = new TreeMap();
            tmap.putAll(map);
            String appkey = "";
            if (SybConstants.SIGN_TYPE.equals("RSA"))
                appkey = SybConstants.SYB_RSATLPUBKEY;
            else if (SybConstants.SIGN_TYPE.equals("SM2"))
                appkey = SybConstants.SYB_SM2TLPUBKEY;
            else
                appkey = SybConstants.SYB_MD5_APPKEY;
            if (SybUtil.validSign(tmap, appkey, SybConstants.SIGN_TYPE)) {
                System.out.println("签名成功");
                return map;
            } else {
                throw new Exception("验证签名失败");
            }

        } else {
            throw new Exception(map.get("retmsg").toString());
        }
    }

    public static void main(String[] args) throws Exception {
        //cancel(1,"order001", "");
//        refund(1, "order001", "");
        //query("order001", "");

        String ss = "0c07717e60b9d85505be7b421027bad6";

        System.out.println(ss.length());


    }

}
