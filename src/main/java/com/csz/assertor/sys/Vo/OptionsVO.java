package com.csz.assertor.sys.Vo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 *@Author: luoluo
 *@date: 2019/12/2
 *
 * 答案视图
 */
@Data
public class OptionsVO {
    private Integer id;

    private String optionText;

    private String optionCode;

    private String optionSort;
}
