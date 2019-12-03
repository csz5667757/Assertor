package com.csz.assertor.sys.DTO;

import lombok.Data;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/2
 */

@Data
public class RecommendedQuestionsEditDTO {

    private Integer id;
    private String description;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer correctAnswer;
    private String analysisText;

    //富文本
    private String descriptionCode;
    private String optionACode;
    private String optionBCode;
    private String optionCCode;
    private String optionDCode;
    private String analysisCode;


}
