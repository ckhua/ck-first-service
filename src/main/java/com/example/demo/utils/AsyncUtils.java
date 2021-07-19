package com.example.demo.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chen kang hua 2021/1/4 14:23
 * @version 1.0.0
 */
@Component
public class AsyncUtils {

  @Async(value = "asyncBuyOrderThread")
  public void asyncStart(NotParamConsumer consumer) {
    consumer.accept();
  }
}
