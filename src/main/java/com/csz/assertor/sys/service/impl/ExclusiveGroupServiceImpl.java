package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.Vo.index.IndexPieVO;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.mapper.ExclusiveGroupMapper;
import com.csz.assertor.sys.service.IExclusiveGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-11-28
 */
@Service
public class ExclusiveGroupServiceImpl extends ServiceImpl<ExclusiveGroupMapper, ExclusiveGroup> implements IExclusiveGroupService {

    @Autowired
    private ExclusiveGroupMapper mapper;

    @Override
    public List<IndexPieVO> getExclusiveGroup() {
        return mapper.getExclusiveGroup();
    }
}
