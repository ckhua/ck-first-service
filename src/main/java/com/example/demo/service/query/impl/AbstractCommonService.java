package com.example.demo.service.query.impl;
import com.example.demo.service.query.ICommonQueryService;

import java.util.function.Function;

/**
 * @Description 查询数据类抽象类
 * @Date 2020/4/15 14:36
 * @Author chen kang hua
 * @Version 1.0
 **/
public abstract class AbstractCommonService<Context, Param, Result> implements ICommonQueryService<Param, Result> {

    @Override
    public Function<Param, Result> queryData() {
        return t -> {
            //构建上下文信息
            Result apply = buildContext()
                    //构建结果信息
                    .andThen(buildResult())
                    .apply(t);
            return apply;
        };
    }

    /**
     * 构建上下文信息
     *
     * @return
     */
    protected abstract Function<Param, Context> buildContext();

    /**
     * 构建结果信息
     *
     * @return
     */
    protected abstract Function<Context, Result> buildResult();
}
