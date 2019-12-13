package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.Exception.OPException;
import com.csz.assertor.sys.Vo.QuestionVO;
import com.csz.assertor.sys.entity.Options;
import com.csz.assertor.sys.entity.Questions;
import com.csz.assertor.sys.mapper.OptionsMapper;
import com.csz.assertor.sys.mapper.QuestionsMapper;
import com.csz.assertor.sys.service.IQuestionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private QuestionsMapper mapper;

    @Autowired
    private OptionsMapper oMapper;

    @Override
    public Page<QuestionVO> SelectQuestions(String categoryGroupId, String exclusiveId, Page<QuestionVO> page) {
        return page.setRecords(this.baseMapper.selectQuestions(categoryGroupId,exclusiveId,page));
    }

    @Override
    public void DeleteQuestion(String questionId) throws OPException {
        if (StringUtils.isNotBlank(questionId)){
            mapper.deleteById(Integer.valueOf(questionId));
            EntityWrapper<Options> wrapper = new EntityWrapper<>();
            wrapper.eq("question_id",questionId);
            List<Options> options = oMapper.selectList(wrapper);
            for (int i=0;i<4;i++){
                if (options.size()>0){
                    if (StringUtils.isNotBlank(options.get(i).getId().toString())){
                        oMapper.deleteById(options.get(i).getId());
                    }
                }
            }
        }
    }
}
