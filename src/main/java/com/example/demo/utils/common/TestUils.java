package com.example.demo.utils.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 类注释
 * @Date 2021/4/12 13:57
 * @Author chen kang hua
 * @Version 1.0
 **/
public class TestUils {

    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    public static void main(String[] args) {
        String before1M = getYearLastMonth(new Date());
        System.out.println(before1M);
    }

    public static String getYearLastMonth(Date d) {
        d = getBefore1M(d);
        return date2String(DATE_FORMAT_YYYYMM, d);
    }

    public static Date getBefore1M(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 日期(时间)转化为字符串.
     *
     * @param formater 日期或时间的格式.
     * @param aDate    java.util.Date类的实例.
     * @return 日期转化后的字符串.
     */
    public static String date2String(String formater, Date aDate) {
        if (formater == null || "".equals(formater))
            return null;
        if (aDate == null)
            return null;
        return (new SimpleDateFormat(formater)).format(aDate);
    }

    public static void testTime() {
        Date ofDay = TimeUtils.getDateStartOfDay(LocalDateTime.now());
        System.out.println(ofDay);
        System.out.println(new Date());


        int minute = LocalDateTime.now()
                .getMinute();
        System.out.println(minute);
    }

    public static void sendMail() {
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("chenkh666@163.com");
        account.setUser("chenkh666@163.com");
        account.setPass("YFRTYDJVULSXDZXY"); //密码
        MailUtil.send(account, CollUtil.newArrayList("chenkh666@163.com"), "测试", "邮件来自Hutool测试", false);
    }


}
