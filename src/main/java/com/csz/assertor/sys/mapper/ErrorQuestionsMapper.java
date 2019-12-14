package com.csz.assertor.sys.mapper;

import com.csz.assertor.sys.entity.ErrorQuestions;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-12-14
 */
public interface ErrorQuestionsMapper extends BaseMapper<ErrorQuestions> {
    Integer selectOneDayError(@Param("date") Date date);
}
