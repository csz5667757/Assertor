package com.csz.assertor.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.csz.assertor.sys.Vo.UserVO;
import com.csz.assertor.sys.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserVO> getUser(Pagination page);
}
