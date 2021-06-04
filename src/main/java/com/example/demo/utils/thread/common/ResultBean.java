package com.example.demo.utils.thread.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;


/**
 * 返回结果统一bean
 * 
 * ResultBean<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2018年4月12日-下午3:49:46 <BR>
 * @version 2.0
 *
 */
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 成功状态
    public static final int SUCCESS = 1;
    // 处理中状态
    public static final int PROCESSING = 0;
    // 失败状态
    public static final int FAIL = -1;

    // 描述
    private String msg = "success";
    // 状态默认成功
    private int code = SUCCESS;
    // 备注
    private String remark;
    // 返回数据
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    /**
     * 使用异常创建结果
     */
    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
    
    /**
     * 
     * 实例化结果默认成功状态<BR>
     * 方法名：newInstance<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:51:26 <BR>
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public static <T> ResultBean<T> newInstance() {
        ResultBean<T> instance = new ResultBean<T>();
        //默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        return instance;
    }
    
    /**
     * 
     * 实例化结果默认成功状态和数据<BR>
     * 方法名：newInstance<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年5月10日-下午2:13:16 <BR>
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public static <T> ResultBean<T> newInstance(T data) {
        ResultBean<T> instance = new ResultBean<T>();
        //默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        instance.data = data;
        return instance;
    }
    
    /**
     * 
     * 实例化返回结果<BR>
     * 方法名：newInstance<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午4:00:53 <BR>
     * @param code
     * @param msg
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public static <T> ResultBean<T> newInstance(int code, String msg) {
        ResultBean<T> instance = new ResultBean<T>();
        //默认返回信息
        instance.code = code;
        instance.msg = msg;
        return instance;
    }
    
    /**
     * 
     * 实例化返回结果<BR>
     * 方法名：newInstance<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午4:00:35 <BR>
     * @param code
     * @param msg
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public static <T> ResultBean<T> newInstance(int code, String msg, T data) {
        ResultBean<T> instance = new ResultBean<T>();
        //默认返回信息
        instance.code = code;
        instance.msg = msg;
        instance.data = data;
        return instance;
    }
    
    /**
     * 
     * 设置返回数据<BR>
     * 方法名：setData<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:52:01 <BR>
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> setData(T data){
        this.data = data;
        return this;
    }
    
    /**
     * 
     * 设置结果描述<BR>
     * 方法名：setMsg<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:52:34 <BR>
     * @param msg
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> setMsg(String msg){
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * 设置状态<BR>
     * 方法名：setCode<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午4:17:56 <BR>
     * @param code
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> setCode(int code){
        this.code = code;
        return this;
    }
    
    /**
     * 
     * 设置备注)<BR>
     * 方法名：setRemark<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午5:47:29 <BR>
     * @param remark
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> setRemark(String remark){
        this.remark = remark;
        return this;
    }
    
    /**
     * 
     * 设置成功描述和返回数据<BR>
     * 方法名：success<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:52:58 <BR>
     * @param msg
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> success(String msg, T data){ 
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
        return this;  
    } 
    
    /**
     * 
     * 设置成功返回结果描述<BR>
     * 方法名：success<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:53:31 <BR>
     * @param msg
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> success(String msg){ 
        this.code = SUCCESS;
        this.msg = msg;
        return this;  
    }
    
    /**
     * 
     * 设置处理中描述和返回数据<BR>
     * 方法名：success<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:52:58 <BR>
     * @param msg
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> processing(String msg, T data){ 
        this.code = PROCESSING;
        this.data = data;
        this.msg = msg;
        return this;  
    } 
    
    /**
     * 
     * 设置处理中返回结果描述<BR>
     * 方法名：success<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:53:31 <BR>
     * @param msg
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> processing(String msg){ 
        this.code = PROCESSING;
        this.msg = msg;
        return this;  
    }
    
    /**
     * 
     * 设置失败返回描述和返回数据<BR>
     * 方法名：fail<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:54:04 <BR>
     * @param msg
     * @param data
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> fail(String msg, T data){ 
        this.code = FAIL;
        this.data = data;
        this.msg = msg;
        return this;  
    } 
    
    /**
     * 
     * 设置失败返回描述<BR>
     * 方法名：fail<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午3:54:32 <BR>
     * @param msg
     * @return ResultBean<T><BR>
     * @exception <BR>
     * @since  2.0
     */
    public ResultBean<T> fail(String msg){ 
        this.code = FAIL;
        this.msg = msg;
        return this;  
    }
    
    public T getData() {  
        return data;  
    }  
    public String getMsg() {  
        return msg;  
    }  
    public int getCode() {  
        return code;  
    }  
    public String getRemark() {  
        return remark;  
    }  
    
    /**
     * 
     * 生成json字符串<BR>
     * 方法名：json<BR>
     * 创建人：wangbeidou <BR>
     * 时间：2018年4月12日-下午4:42:28 <BR>
     * @return String<BR>
     * @exception <BR>
     * @since  2.0
     */
    public String json(){
        return JSON.toJSONString(this);
    }
}