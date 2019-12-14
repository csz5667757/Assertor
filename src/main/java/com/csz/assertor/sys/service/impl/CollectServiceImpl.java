package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.entity.Collect;
import com.csz.assertor.sys.mapper.CollectMapper;
import com.csz.assertor.sys.service.ICollectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-12-14
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {

    @Autowired
    private CollectMapper mapper;
    @Override
    public Integer selectOneDayCollect(Date date) {
        return mapper.selectOneDayCollect(date);
    }
}
