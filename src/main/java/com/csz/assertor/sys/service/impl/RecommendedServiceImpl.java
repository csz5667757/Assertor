package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.sys.DTO.RecommendedDTO;
import com.csz.assertor.sys.Vo.RecommendedVO;
import com.csz.assertor.sys.entity.Recommended;
import com.csz.assertor.sys.mapper.RecommendedMapper;
import com.csz.assertor.sys.service.IRecommendedService;
import com.csz.assertor.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-12-01
 */
@Service
public class RecommendedServiceImpl extends ServiceImpl<RecommendedMapper, Recommended> implements IRecommendedService {

    @Autowired
    private RecommendedMapper mapper;

    @Override
    public Page<RecommendedVO> SelectRecommendeds(Integer techCategoryId, Integer level, Page<RecommendedVO> page) {
        return page.setRecords(this.baseMapper.selectRecommended(techCategoryId,level,page));
    }

    @Override
    public void addRecommended(RecommendedDTO recommendedDTO) {
        Recommended recommended = new Recommended();
        recommended = BeanUtil.copyBean(recommendedDTO,Recommended.class);
        mapper.insert(recommended);
    }

}
