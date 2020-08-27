package com.example.demo.utils.common;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MemoryPageHelper
 * 内存分页 (数据量超大时请勿使用 oom)
 * @author chen kang hua
 * @description
 * @date 2019/11/28
 */
public class MemoryPageHelper {

    /**
     * 内存分页
     *
     * @param pageNo  页数
     * @param pageSize  每页数量
     * @param fullData  数据
     * @return
     */
    public static <T> Page<T> pagination(Integer pageNo, Integer pageSize, List<T> fullData) {
        Page<T> page = new Page<>(pageNo, pageSize);
        Integer totalPage = fullData.size() % pageSize == 0 ? fullData.size() / pageSize : (fullData.size() / pageSize) + 1;
        page.setTotalRow(fullData.size());
        page.setTotalPage(totalPage);
        List pageList = fullData.stream().skip(pageSize * (pageNo - 1)).limit(pageSize).collect(Collectors.toList());
        page.setList(pageList);
        return page;
    }
}
