package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csz.assertor.sys.Vo.index.IndexPieVO;
import com.csz.assertor.sys.entity.ExclusiveGroup;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-11-28
 */
public interface ExclusiveGroupMapper extends BaseMapper<ExclusiveGroup> {
    List<IndexPieVO> getExclusiveGroup();
}
