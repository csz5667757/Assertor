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
 * @since 2019-11-28
 */
@Data
public class Exclusive {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("category_group_id")
    private Integer categoryGroupId;

    @TableField("exclusive_title")
    private String exclusiveTitle;

}
