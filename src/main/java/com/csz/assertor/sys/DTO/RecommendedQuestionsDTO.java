package com.csz.assertor.sys.DTO;

import lombok.Data;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/2
 */
@Data
public class RecommendedQuestionsDTO {

    private Integer recommenedId;
    private String description;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer correctAnswer;
    private String analysisCode;

}
