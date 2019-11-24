package com.csz.assertor.sys.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.csz.assertor.rest.ResultEnum;
import com.csz.assertor.rest.ResultGenerator;
import com.csz.assertor.rest.response.Response;
import com.csz.assertor.sys.entity.StudentGrade;
import com.csz.assertor.sys.mapper.StudentGradeMapper;
import com.csz.assertor.sys.service.IStudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@RestController
@RequestMapping("/sys/grade")
public class StudentGradeEntity{
    @Autowired
    private IStudentGradeService studentGradeService;

    @RequestMapping("all")
    public Response<List> allGrade(){
        EntityWrapper<StudentGrade> wrapper = new EntityWrapper<>();
        List<StudentGrade> studentGrades = studentGradeService.selectList(wrapper);
        return ResultGenerator.ok(studentGrades);
    }
}
