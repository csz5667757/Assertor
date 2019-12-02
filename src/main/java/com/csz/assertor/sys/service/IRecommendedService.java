package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.Vo.RecommendedVO;
import com.csz.assertor.sys.entity.Recommended;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-12-01
 */
public interface IRecommendedService extends IService<Recommended> {
    Page<RecommendedVO> SelectRecommendeds(Integer techCategoryId, Integer level, Page<RecommendedVO> page);
}