package com.csz.assertor.sys.DTO;

import lombok.Data;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/1
 */

@Data
public class QuestionsEditDTO {

    private Integer id;
    private Integer exclusiveId;
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
