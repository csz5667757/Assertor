package com.csz.assertor.sys.mapper;

import com.csz.assertor.sys.entity.Collect;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-12-14
 */
public interface CollectMapper extends BaseMapper<Collect> {
    Integer selectOneDayCollect(@Param("date")Date date);
}
