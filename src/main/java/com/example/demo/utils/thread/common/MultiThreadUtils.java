package com.example.demo.utils.thread.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * MultiThreadUtils<BR> 创建人:wangbeidou <BR> 时间：2018年8月8日-下午8:20:42 <BR>
 *
 * @version 2.0
 */
public class MultiThreadUtils<E , T> {

  private static Logger logger = LoggerFactory.getLogger(MultiThreadUtils.class);

  // 线程个数，如不赋值，默认为5
  private int threadCount = 5;
  // 具体业务任务
  private ITask<ResultBean<T>, E> task;
  // 线程池管理器
  private CompletionService<ResultBean> pool = null;

  /**
   * 初始化线程池和线程个数<BR> 方法名：newInstance<BR> 创建人：wangbeidou <BR> 时间：2018年8月8日-下午8:22:00 <BR>
   *
   * @param threadCount
   * @return MultiThreadUtils<BR>
   * @throws <BR>
   * @since 2.0
   */
  public static MultiThreadUtils newInstance(int threadCount) {
    MultiThreadUtils instance = new MultiThreadUtils();
    threadCount = threadCount;
    instance.setThreadCount(threadCount);

    return instance;
  }

  /**
   * 多线程分批执行list中的任务<BR> 方法名：execute<BR> 创建人：wangbeidou <BR> 时间：2018年8月8日-下午8:22:31 <BR>
   *
   * @param data   线程处理的大数据量list
   * @param params 处理数据是辅助参数传递
   * @param task   具体执行业务的任务接口
   * @return ResultBean<BR>
   * @throws <BR>
   * @since 2.0
   */
  @SuppressWarnings("rawtypes")
  public ResultBean execute(List<E> data, Map<String, Object> params,
      ITask<ResultBean<T>, E> task) {
    // 创建线程池
    ExecutorService threadpool = Executors.newFixedThreadPool(threadCount);
    // 根据线程池初始化线程池管理器
    pool = new ExecutorCompletionService<ResultBean>(threadpool);
    // 开始时间（ms）
    long l = System.currentTimeMillis();
    ListOperateUtil.spitListAccept(data, 2000, (subData) -> {
      // 将数据分配给各个线程
      HandleCallable execute = new HandleCallable<E,T>(UUID.randomUUID().toString(), subData, params,
          task);
      // 将线程加入到线程池
      pool.submit(execute);
    });
    // 总的返回结果集
    List<ResultBean<E>> result = new ArrayList<>();
    for (int i = 0; i < threadCount; i++) {
      // 每个线程处理结果集
      ResultBean<List<ResultBean<E>>> threadResult = new ResultBean<>();
      try {
        threadResult = pool.take().get();
        result.addAll(threadResult.getData());
      } catch (InterruptedException e) {
        ResultBean<E> failResult = new ResultBean<>();
        failResult.fail(e.getMessage());
        result.add(failResult);
        e.printStackTrace();
      } catch (ExecutionException e) {
        ResultBean<E> failResult = new ResultBean<>();
        failResult.fail(e.getMessage());
        result.add(failResult);
        e.printStackTrace();
      } catch (Exception e) {
        ResultBean<E> failResult = new ResultBean<>();
        failResult.fail(e.getMessage());
        result.add(failResult);
        e.printStackTrace();
      }
    }
    // 关闭线程池
    threadpool.shutdownNow();
    // 执行结束时间
    long end_l = System.currentTimeMillis();
    logger.info("总耗时:{}ms", (end_l - l));
    return ResultBean.newInstance().setData(result);
  }

  public int getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(int threadCount) {
    this.threadCount = threadCount;
  }

}