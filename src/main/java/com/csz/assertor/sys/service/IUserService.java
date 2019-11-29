package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.Vo.UserVO;
import com.csz.assertor.sys.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
public interface IUserService extends IService<User> {
    Page<UserVO> getUser(Page<UserVO> page);
}
