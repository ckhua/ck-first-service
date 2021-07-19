package com.example.demo.utils.tonglian.pay;

import java.util.Map;
import java.util.TreeMap;

public class SybPayService {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, String> handleResult(String result) throws Exception {
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

    /**
     * @param trxamt
     * @param reqsn
     * @param paytype
     * @param body
     * @param remark
     * @param acct
     * @param validtime
     * @param notify_url
     * @param limit_pay
     * @param idno
     * @param truename
     * @param asinfo
     * @param sub_appid
     * @param goods_tag           单品优惠信息
     * @param chnlstoreid
     * @param subbranch
     * @param extendparams具体见接口文档
     * @param cusip               限云闪付JS支付业务
     * @param fqnum               限支付宝分期业务
     * @return
     * @throws Exception
     */
    public Map<String, String> pay(long trxamt, String reqsn, String paytype, String body, String remark, String acct, String validtime, String notify_url, String limit_pay,
                                   String idno, String truename, String asinfo, String sub_appid, String goods_tag, String benefitdetail, String chnlstoreid, String subbranch, String extendparams, String cusip, String fqnum) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.SYB_APIURL + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("charset", "utf-8");
        params.put("appid", SybConstants.SYB_APPID);
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", reqsn);
        params.put("paytype", paytype);
        params.put("randomstr", SybUtil.getValidatecode(8));
        params.put("body", body);
        params.put("remark", remark);
        params.put("validtime", validtime);
        params.put("notify_url", notify_url);
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

    public Map<String, String> cancel(long trxamt, String reqsn, String oldtrxid, String oldreqsn) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.SYB_APIURL + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", reqsn);
        params.put("oldtrxid", oldtrxid);
        params.put("oldreqsn", oldreqsn);
        params.put("randomstr", SybUtil.getValidatecode(8));
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

    public Map<String, String> refund(long trxamt, String reqsn, String oldtrxid, String oldreqsn) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.SYB_APIURL + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", reqsn);
        params.put("oldreqsn", oldreqsn);
        params.put("oldtrxid", oldtrxid);
        params.put("randomstr", SybUtil.getValidatecode(8));
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

    public Map<String, String> query(String reqsn, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.SYB_APIURL + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("reqsn", reqsn);
        params.put("trxid", trxid);
        params.put("randomstr", SybUtil.getValidatecode(8));
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

    public Map<String, String> scanPay(long trxamt, String reqsn, String body, String remark, String authcode, String limit_pay, String idno, String truename, String asinfo) throws Exception {
        // TODO Auto-generated method stub
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstants.SYB_APIURL + "/scanqrpay");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        if (!SybUtil.isEmpty(SybConstants.SYB_ORGID))
            params.put("orgid", SybConstants.SYB_ORGID);
        params.put("cusid", SybConstants.SYB_CUSID);
        params.put("appid", SybConstants.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", reqsn);
        params.put("randomstr", SybUtil.getValidatecode(8));
        params.put("body", body);
        params.put("remark", remark);
        params.put("authcode", authcode);
        params.put("limit_pay", limit_pay);
        params.put("asinfo", asinfo);
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


}
