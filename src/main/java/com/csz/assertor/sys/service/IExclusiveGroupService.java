package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.Vo.index.IndexPieVO;
import com.csz.assertor.sys.entity.ExclusiveGroup;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-11-28
 */
public interface IExclusiveGroupService extends IService<ExclusiveGroup> {
    List<IndexPieVO> getExclusiveGroup();
}
