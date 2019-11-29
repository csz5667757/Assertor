package com.csz.assertor.rest;


import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.response.Paging;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.utils.BeanUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 生成restful返回对象
 */

public class ResultGenerator {

    /**
     * 通过mybatis-plus的{@link Page}生成{@link Paging}
     * @param page mybatis-plus的Page
     * @param clazz 返回前端视图层的Class
     * @return 分页对象
     */

    public static <T> Response<Paging<T>> page(Page<T> page , Class clazz){
        List<T> list = Lists.newArrayList();
        for (Object source : page.getRecords()) {
            T taget = BeanUtil.copyBean(source,clazz);
            list.add(taget);
        }
        int totalCount = (int) page.getTotal();
        int curPage = (int)page.getCurrent();
        int pageSize = (int)page.getSize();
        int totalPage = (int) page.getPages();

        Paging paging = new Paging<>(totalCount,pageSize,totalPage,curPage,list);
        return new Response<Paging<T>>(ResultEnum.SUCCESS,paging);
    }

    /**
     * 生成{@link Paging}
     * @param toalCount 总记录数
     * @param pageSize  每页记录数
     * @param curPage    当前页
     * @param totalPage  总页数
     * @param list  列表数据
     * @param <T>   分页对象
     * @return
     */
    public static <T> Response<Paging<T>> page(int toalCount,int pageSize,int curPage,int totalPage,List<T> list){
        Paging paging = new Paging<>(toalCount,pageSize,totalPage,curPage,list);
        return new Response<Paging<T>>(ResultEnum.SUCCESS,paging);
    }

    /**
     * 请求成功
     * @return {@link Response}
     */
    public static Response ok(){
        return new Response<>(ResultEnum.SUCCESS,null);
    }

    /**
     * 请求成功
     * @param message
     * @return {@link Response}
     */
    public static Response ok(String message){
        Response<String> response = new Response<>(ResultEnum.SUCCESS,null);
        response.setMessage(message);
        return response;
    }

    /**
     * 请求成功
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> ok(T data){
        Response<T> response = new Response<>(ResultEnum.SUCCESS,data);
        return response;
    }


    /**
     * 请求成功
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> table(T data){
        Response<T> response = new Response<>(ResultEnum.TABLESUCC,data);
        return response;
    }


    /**
     * 请求成功
     * @param message 自定义消息文本
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> ok(String message,T data){
        Response<T> response = new Response<>(ResultEnum.SUCCESS,data);
        response.setMessage(message);
        return response;
    }

    /**
     * 请求失败
     * @return {@link Response}
     */
    public static Response failure(){
        return new Response<>(ResultEnum.INTERNAL_SERVER_ERROR,null);
    }

    /**
     * 请求失败
     * @param message 自定义消息文本
     * @return {@link Response}
     */
    public static Response failure(String message){
        Response<String> response = new Response<>(ResultEnum.INTERNAL_SERVER_ERROR,null);
        response.setMessage(message);
        return response;
    }

    /**
     * 请求失败
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> failure(T data){
        Response<T> response = new Response<>(ResultEnum.INTERNAL_SERVER_ERROR,data);
        return response;
    }

    /**
     * 请求失败
     * 自定义返回的对象调用put方法
     * @param code 自定义状态值
     * @param message 自定义消息文本
     * @return {@link Response}
     */
    public static Response failure(int code,String message){
        return new Response<>(code,message,null);
    }

    /**
     *请求失败
     * 自定义返回的对象调用put方法
     * @param message 自定义消息文本
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> failure(String message,T data){
        Response<T> response = new Response<>(ResultEnum.INTERNAL_SERVER_ERROR,data);
        response.setMessage(message);
        return response;
    }

    /**
     * 请求失败
     * 自定义返回的对象调用put方法
     * @param message 自定义消息文本
     * @param code 自定义状态值
     * @param data 返回的对象
     * @return {@link Response}
     */
    public static <T> Response<T> failure(String message,int code,T data){
        return new Response<>(code,message,data);
    }
    }
