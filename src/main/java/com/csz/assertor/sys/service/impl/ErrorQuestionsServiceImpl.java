package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.entity.ErrorQuestions;
import com.csz.assertor.sys.mapper.ErrorQuestionsMapper;
import com.csz.assertor.sys.service.IErrorQuestionsService;
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
public class ErrorQuestionsServiceImpl extends ServiceImpl<ErrorQuestionsMapper, ErrorQuestions> implements IErrorQuestionsService {

    @Autowired
    private ErrorQuestionsMapper mapper;
    @Override
    public Integer selectOneDayError(Date date) {
        return mapper.selectOneDayError(date);
    }
}
