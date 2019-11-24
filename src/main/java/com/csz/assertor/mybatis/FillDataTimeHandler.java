package com.csz.assertor.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/24
 */
public class FillDataTimeHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName("gmtCreate",now,metaObject);
        setFieldValByName("gmtModified",now,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName("gmtModified",now,metaObject);
    }
}
