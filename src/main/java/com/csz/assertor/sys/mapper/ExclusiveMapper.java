package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csz.assertor.sys.Vo.ExclusiveVO;
import com.csz.assertor.sys.entity.Exclusive;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-11-28
 */
public interface ExclusiveMapper extends BaseMapper<Exclusive> {
    List<ExclusiveVO> selectExclusive();
}
