package com.csz.assertor.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@Data
public class User extends BaseEntity<User> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private static final long serialVersionUID = 1L;

    private String name;

    private String address;

    private LocalDateTime useDate;

    private String username;

    private String password;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
