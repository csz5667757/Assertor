package com.csz.assertor.sys.DTO;

import lombok.Data;

/**
 * @author Assertor
 * @Description 套题修改入参
 * @Date：2019/12/4
 */

@Data
public class UpdateRecommendDTO {
    private Integer id;
    private Integer tid;
    private Integer level;
    private String title;
}
