package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
public interface QuestionsMapper extends BaseMapper<Questions> {
    List<QuestionVO> selectQuestions(@Param("categoryGroupId") String categoryGroupId,
                                     @Param("exclusiveId") String exclusiveId, Page page);
}
