package com.example.demo.service.filter;

import java.util.List;

/**
 * @Description 关键字过滤服务
 * @Date 2020/6/18 16:23
 * @Author chen kang hua
 * @Version 1.0
 **/
public interface ITextWordsFilterService<T> {


    /**
     * 过滤关键字
     *
     * @param sourceList
     * @return sourceList
     */
    List<T> filterText(List<T> sourceList);

    /**
     * 过滤关键字
     *
     * @param source
     * @return
     */
    T filterText(T source);
}
