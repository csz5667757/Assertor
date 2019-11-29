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
 * @since 2019-11-27
 */
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    @TableField("tech_category_id")
    private Integer techCategoryId;

    @TableField("is_skip")
    private Integer isSkip;

}
