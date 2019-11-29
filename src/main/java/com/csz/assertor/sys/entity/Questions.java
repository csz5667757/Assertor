package com.csz.assertor.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.csz.assertor.mybatis.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@Data
public class Questions{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("recommended_id")
    private Integer recommendedId;

    @TableField("exclusive_id")
    private Integer exclusiveId;

    private String description;

    private String code;

    @TableField("answer_id")
    private Integer answerId;

    @TableField("analysis_text")
    private String analysisText;

    @TableField("analysis_code")
    private String analysisCode;

}
