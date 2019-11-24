package com.csz.assertor.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@Data
public class StudentGrade extends BaseEntity<StudentGrade> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String subject;

    private Integer grade;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
