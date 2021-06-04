package com.example.demo.utils.common;

import java.util.Date;

/**
 * @author chen kang hua 2020/12/22 15:02
 * @version 1.0.0
 */
public class TestUtils {


  public static void main(String[] args) {


    String message = "切片编号：%s 生效时间 %s 计算完成";
    String format = String.format(message, 1, 2);

    System.out.println(format);
  }
}
