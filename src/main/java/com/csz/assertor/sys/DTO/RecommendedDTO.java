package com.csz.assertor.sys.DTO;

import lombok.Data;

/**
 * @author Assertor
 * @Description 上传套题入参
 * @Date：2019/12/3
 */

@Data
public class RecommendedDTO{

    private String title;
    private Integer level;
    private Integer techCategoryId;

}
