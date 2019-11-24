package com.csz.assertor.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * spring BeanUtils扩展类
 */

public class BeanUtil extends BeanUtils {
    public static <T> T copyBean(Object source , Class clazz){
        try{
            if (source == null) {
                return null;
            }else{
                T o =(T)clazz.newInstance();
                copyProperties(source,o);
                return o;
            }
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List copyList(List<T> sourceList , Class clazz){
        List<T> beanList = Lists.newArrayList();
        for (int i = 0;i < sourceList.size();i++)
        try{
            Object o = clazz.newInstance();
            copyProperties(sourceList.get(i),o);
            beanList.add((T)o);
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return beanList;
    }
}
