package com.example.demo.utils.tonglian.pay.test;


import com.example.demo.utils.tonglian.pay.SybPayService;

import java.util.Map;


public class ApiTestV2 {
	public static void main(String[] args) throws Exception {
//		String testPay = testPay("A01");//统一下单，异步类交易
//		System.out.println(testPay);
//		System.out.println(SybConstants.SYB_RSACUSPRIKEY.length());
//		System.out.println(SybConstants.SYB_SM2PPRIVATEKEY.length());
//		testScanPay();//统一扫码，被扫交易
//		testCancel();//撤销
		testRefund();//退款
		testQuery();//查询
	}


	public static void testScanPay() throws Exception {
		// TODO Auto-generated method stub
		SybPayService service = new SybPayService();
		String reqsn = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = service.scanPay(1, reqsn, "标题", "备注", "134775931316089668", "", "", "", "");
		print(map);
	}

	public static void testQuery() throws Exception {
		SybPayService service = new SybPayService();
		Map<String, String> map = service.query("", "112121100002386773");
		print(map);
	}

	public static void testRefund() throws Exception {
		SybPayService service = new SybPayService();
		String reqsn = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = service.refund(1, reqsn, "", "20160712167578.2547");
		print(map);
	}

	public static void testCancel() throws Exception {
		SybPayService service = new SybPayService();
		String reqsn = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = service.cancel(1, reqsn, "112094120001088316", "");
		print(map);
	}

	public static String testPay(String payType) throws Exception {
		SybPayService service = new SybPayService();
		//订单号
		String reqsn = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = service.pay(1, reqsn, payType, "标题", "备注", "", "123", "http://chenkh.ticp.vip/reBaidu", "", "", "", "", "", "", "", "", "", "", "", "");
		//
		return map.get("payinfo");
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
