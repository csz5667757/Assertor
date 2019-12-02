package com.csz.assertor.sys.Vo;

import lombok.Data;


/**
 * @author Assertor
 * @Description
 * @Date：2019/11/28
 * 查询题目接口入参
 */
@Data
public class QuestionVO {

    private Integer id;

    private String description;

    private String title;

    private String exclusiveTitle;

    private String optionCode;

    private String optionText;
}
