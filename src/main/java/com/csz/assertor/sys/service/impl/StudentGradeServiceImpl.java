package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.entity.StudentGrade;
import com.csz.assertor.sys.mapper.StudentGradeMapper;
import com.csz.assertor.sys.service.IStudentGradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@Service
public class StudentGradeServiceImpl extends ServiceImpl<StudentGradeMapper, StudentGrade>
        implements IStudentGradeService {

}
