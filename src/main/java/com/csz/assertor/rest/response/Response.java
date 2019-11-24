package com.csz.assertor.rest.response;

import com.csz.assertor.rest.ResultEnum;

/**
 * restful 全局返回对象
 * @param <T>
 */
public class Response<T> {

    /**
     * 返回code
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     *私有的构造函数
     * @param resultEnum {@link ResultEnum} 公共消息枚举
     * @param data
     */
    public Response(ResultEnum resultEnum,T data){
        this.code=resultEnum.getCode();
        this.message=resultEnum.getMessage();
        this.data=data;
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
