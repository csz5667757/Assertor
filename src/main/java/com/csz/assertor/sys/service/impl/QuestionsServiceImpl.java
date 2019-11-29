package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.mapper.QuestionsMapper;
import com.csz.assertor.sys.service.IQuestionsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements IQuestionsService {
    @Override
    public Page<QuestionVO> SelectQuestions(String categoryGroupId, String exclusiveId, Page<QuestionVO> page) {
        return page.setRecords(this.baseMapper.SelectQuestions(categoryGroupId,exclusiveId,page));
    }
}
