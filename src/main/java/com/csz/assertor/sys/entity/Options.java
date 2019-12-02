package com.csz.assertor.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author assertor
 * @since 2019-12-01
 */
@Data
public class Options{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("question_id")
    private Integer questionId;

    @TableField("option_text")
    private String optionText;

    @TableField("option_code")
    private String optionCode;

    @TableField("option_sort")
    private String optionSort;

}
