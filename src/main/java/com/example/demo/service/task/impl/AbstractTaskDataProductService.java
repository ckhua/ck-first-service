package com.example.demo.service.task.impl;
import com.example.demo.model.DataTaskRecord;
import com.example.demo.service.task.ITaskDataProductService;

/**
 * @Description redisson 生产处理类
 * @Date 2020/4/15 17:03
 * @Author chen kang hua
 * @Version 1.0
 **/
public abstract class AbstractTaskDataProductService implements ITaskDataProductService {


    /**
     * 获取对应业务类型
     * @return
     */
    protected abstract String getDataType();

    /**
     * 虎丘需要处理数据
     * @return
     */
    protected abstract DataTaskRecord getTaskRecord();

}
