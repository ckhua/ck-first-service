package com.example.demo.utils.common;

import java.util.List;
/**
 * CheckListUtils
 * 校验集合信息
 * @date 2019/11/28
 */
public class CheckListUtils {

    public static <T> Boolean checkListIsEmpty(List<T> paramList) {

        if (null == paramList || paramList.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static <T> Boolean checkListIsNotEmpty(List<T> paramList) {

        if (null != paramList && !paramList.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
