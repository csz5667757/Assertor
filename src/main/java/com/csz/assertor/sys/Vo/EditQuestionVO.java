package com.csz.assertor.sys.Vo;

import com.csz.assertor.sys.entity.Options;
import lombok.Data;

import java.util.List;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/1
 */

@Data
public class EditQuestionVO {

    private Integer exclusiveId;
    private Integer categoryGroupId;
    private Integer answerId;
    private String exclusiveTitle;
    private String egTitle;
    private String description;
    private List<Options> options;
    private String analysisText;
    private String answer;

    //富文本
    private String descriptionCode;
    private String analysisCode;

}
