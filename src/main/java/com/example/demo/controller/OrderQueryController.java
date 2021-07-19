package com.example.demo.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.example.demo.aop.redis.limit.RedisRateLimiter;
import com.example.demo.enums.CommonQueryServiceType;
import com.example.demo.exception.OrderCommonException;
import com.example.demo.service.query.ICommonQueryService;
import com.example.demo.utils.tonglian.pay.SybConstants;
import com.example.demo.utils.tonglian.pay.SybUtil;
import com.example.demo.utils.tonglian.pay.test.ApiTestV2;
import com.example.demo.utils.tonglian.pay.test.WangYinApiTestV2;
import com.example.demo.vo.DataCommonQueryVO;
import com.example.demo.vo.DataQueryParamVO;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @Description 数据查询类
 * @Date 2020/4/14 11:28
 * @Author chen kang hua
 * @Version 1.0
 **/
@Api(tags = {"first-数据查询"})
@Controller
public class OrderQueryController {

    /**
     * 将 ICommonQueryService 装配进入map
     */
    @Autowired
    private Map<String, ICommonQueryService> commonQueryServiceMap;

    private final static String URL = "https://my.oschina.net/piaoxianren/blog/4791620";

    @ApiOperation(value = "查询简单数据", notes = "查询简单数据")
    @PostMapping(value = "query/order/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RedisRateLimiter(value = 2, key = "queryOrderData")
    @ResponseBody
    public List<DataCommonQueryVO> queryOrder(@ApiParam(value = "编号", required = false) @RequestParam(value = "recordId", required = false) Integer recordId,
                                              @ApiParam(value = "查询信息", required = false) @RequestBody DataQueryParamVO commonQueryParamVO) {


        //根据业务信息 获取对应服务信息
        String serviceName = Joiner.on("").join(ICommonQueryService.class.getSimpleName(), "_", CommonQueryServiceType.ORDER.getCode());
        commonQueryServiceMap.get(serviceName);
        ICommonQueryService queryService = Optional.ofNullable(serviceName)
                .map(commonQueryServiceMap::get)
                .orElseThrow(OrderCommonException.QUERY_ORDER_DATA_EXCEPTION.exception());

        //执行业务信息
        List<DataCommonQueryVO> dataCommonQueryVOList = Optional.ofNullable(commonQueryParamVO)
                .map(vo -> queryService.queryData().apply(vo))
                .map(vo -> (DataCommonQueryVO) vo)
                .map(Lists::newArrayList)
                .orElse(null);

        //真实业务需封装状态码
        return dataCommonQueryVOList;
    }

    @ApiOperation(value = "查询简单数据(测试限流)", notes = "查询简单数据(测试限流)")
    @GetMapping(value = "query/order/data/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RedisRateLimiter(value = 1, key = "queryOrderData")
    @ResponseBody
    public String queryOrderTest() {
        return "ooo";
    }

    /**
     * 生成简单二维码
     *
     * @return
     */
    @GetMapping("/generateQRCode")
    public void generateQRCode(HttpServletResponse response) {
        BufferedImage bufferedImage = QrCodeUtil.generate("https://www.baidu.com", 300, 300);
        try {
            //以JPEG格式向客户端发送
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(bufferedImage, "PNG", os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成简单二维码
     *
     * @return
     */
    @PostMapping("/reBaidu")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 网银支付
     *
     * @return
     */
    @GetMapping("/test/wangyin/pay/{type}")
    @ResponseBody
    public void queryListPay(HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        String pay = "";
        if ("1".equals(type)) {
            pay = ApiTestV2.testPay("W01");
        } else if ("2".equals(type)) {
            pay = ApiTestV2.testPay("A01");
        } else {
            pay = WangYinApiTestV2.testPay();
        }
        try {
            ServletOutputStream os = response.getOutputStream();
            if (Lists.newArrayList("1", "2").contains(type)) {
                BufferedImage bufferedImage = QrCodeUtil.generate(pay, 300, 300);
                ImageIO.write(bufferedImage, "PNG", os);
            } else {
                IoUtil.write(os, Boolean.TRUE, pay.getBytes());
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 网银支付
     *
     * @return
     */
    @PostMapping("/test/pay/{type}")
    @ResponseBody
    public void testPay(HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        String pay = "";
        if ("1".equals(type)) {
            pay = ApiTestV2.testPay("W01");
        } else if ("2".equals(type)) {
            pay = ApiTestV2.testPay("A01");
        } else {
            pay = WangYinApiTestV2.testPay();
        }
        try {
            ServletOutputStream os = response.getOutputStream();
            if (Lists.newArrayList("1", "2").contains(type)) {
                BufferedImage bufferedImage = QrCodeUtil.generate(pay, 300, 300);
                ImageIO.write(bufferedImage, "PNG", os);
            } else {
                IoUtil.write(os, Boolean.TRUE, pay.getBytes());
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
