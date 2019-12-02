package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.sys.DTO.GetOneQuestionDto;
import com.csz.assertor.sys.Vo.OptionsVO;
import com.csz.assertor.sys.Vo.QuestionAndOptionsVO;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Options;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.mapper.OptionsMapper;
import com.csz.assertor.sys.mapper.QuestionsMapper;
import com.csz.assertor.sys.service.IQuestionsService;
import com.csz.assertor.utils.BeanUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public Page<QuestionVO> SelectQuestions(String categoryGroupId, String exclusiveId, Page<QuestionVO> page) {
        return page.setRecords(this.baseMapper.selectQuestions(categoryGroupId,exclusiveId,page));
    }

    @Override
    public Page<QuestionAndOptionsVO> getOne(GetOneQuestionDto getOneQuestionDto) {
        //创建vo集合
        ArrayList<QuestionAndOptionsVO> objects = Lists.newArrayList();

        EntityWrapper<Questions> wrapper = new EntityWrapper<>();
        Page<QuestionAndOptionsVO> page = new Page<>();
        page.setSize(getOneQuestionDto.getSize());
        page.setCurrent(getOneQuestionDto.getCurrent());
        wrapper.eq("exclusive_id",getOneQuestionDto.getExclusiveId());
        //拿到题目
        List<Questions> questions = questionsMapper.selectPage(page,wrapper);

        EntityWrapper<Options> optionsEntityWrapper = new EntityWrapper<>();
        //遍历查询答案
        for (Questions question:questions
             ) {
            QuestionAndOptionsVO qao = BeanUtil.copyBean(question, QuestionAndOptionsVO.class);
            Wrapper<Options> optionsWrapper = optionsEntityWrapper.eq("question_id", question.getId());
            List<Options> options = optionsMapper.selectList(optionsWrapper);
            List<OptionsVO> copyList = BeanUtil.copyList(options, OptionsVO.class);
            qao.setOptions(copyList);
            //把答案放入题目vo中去
            objects.add(qao);
        }
        page.setRecords(objects);
        return page;
    }
}
