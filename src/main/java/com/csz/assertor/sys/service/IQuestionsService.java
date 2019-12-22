package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Questions;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
public interface IQuestionsService extends IService<Questions> {
    Page<QuestionVO> SelectQuestions(String categoryGroupId, String exclusiveId,String description, Page<QuestionVO> page);

    void DeleteQuestion(String questionId);
}
