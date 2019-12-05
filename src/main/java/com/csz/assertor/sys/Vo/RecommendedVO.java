package com.csz.assertor.sys.Vo;

import lombok.Data;

/**
 * @author Assertor
 * @Description
 * @Date：2019/12/1
 */

@Data
public class RecommendedVO {

    private Integer id ;
    //类目id
    private Integer tid ;
    private Float level;
    private String title;
    private String name;
    private String questionNum;

}
