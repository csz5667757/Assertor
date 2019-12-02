package com.csz.assertor.sys.Vo;

import lombok.Data;

import java.util.List;

/**
 *@Author: luoluo
 *@date: 2019/12/2
 * 题目和答案返回实体
 */
@Data
public class QuestionAndOptionsVO {

    private Integer id;

    private Integer recommendedId;

    private Integer exclusiveId;

    private String description;

    private String code;

    private Integer answerId;

    private String analysisText;

    private String analysisCode;

    private List<OptionsVO> options;

}
