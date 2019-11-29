package com.csz.assertor.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("exclusive_group")
public class ExclusiveGroup {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

}
