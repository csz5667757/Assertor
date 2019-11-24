package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.csz.assertor.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
