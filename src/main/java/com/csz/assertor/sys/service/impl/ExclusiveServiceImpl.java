package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.sys.Vo.ExclusiveVO;
import com.csz.assertor.sys.entity.Exclusive;
import com.csz.assertor.sys.entity.ExclusiveGroup;
import com.csz.assertor.sys.mapper.ExclusiveGroupMapper;
import com.csz.assertor.sys.mapper.ExclusiveMapper;
import com.csz.assertor.sys.service.IExclusiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.utils.BeanUtil;
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
public class ExclusiveServiceImpl extends ServiceImpl<ExclusiveMapper, Exclusive> implements IExclusiveService {

    @Autowired
    private ExclusiveMapper eMapper;

    @Autowired
    private ExclusiveGroupMapper egMapper;

    @Override
    public List<ExclusiveVO> selectExclusive() {
        List<ExclusiveVO> exclusiveVOList = eMapper.selectExclusive();
        for(int n = 0;n<exclusiveVOList.size(); n++){
            ExclusiveVO exclusiveVO = exclusiveVOList.get(n);
            exclusiveVO.setId(exclusiveVO.getId()+1000);
        }
        List<ExclusiveGroup> exclusiveGroupList = egMapper.selectList(new EntityWrapper<>());
        for(int i = 0; i<exclusiveGroupList.size();i++){
            ExclusiveVO exclusiveVO = BeanUtil.copyBean(exclusiveGroupList.get(i), ExclusiveVO.class);
            exclusiveVO.setPid(0);
            exclusiveVOList.add(exclusiveVO);
        }
        return exclusiveVOList;
    }
}
