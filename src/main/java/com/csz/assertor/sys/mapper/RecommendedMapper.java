package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.sys.Vo.RecommendedVO;
import com.csz.assertor.sys.Vo.UpdateReommendedVO;
import com.csz.assertor.sys.Vo.index.IndexPieVO;
import com.csz.assertor.sys.entity.Recommended;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-12-01
 */
public interface RecommendedMapper extends BaseMapper<Recommended> {
    List<RecommendedVO> selectRecommended(@Param("techCategoryId") Integer techCategoryId,
                                        @Param("level") Integer level, Page page);
    UpdateReommendedVO getOne(@Param("recommendedId") Integer recommendedId);

    List<IndexPieVO> getRecommended();
}
