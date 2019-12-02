package com.csz.assertor.sys.Vo;

import com.csz.assertor.sys.entity.Options;
import lombok.Data;

import java.util.List;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/2
 */

@Data
public class EditRecommendedVO {

    private Integer answerId;
    private String description;
    private List<Options> options;
    private String analysisCode;
    private String answer;


}
