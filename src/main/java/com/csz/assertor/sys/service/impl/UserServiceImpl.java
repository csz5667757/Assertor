package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.entity.User;
import com.csz.assertor.sys.mapper.UserMapper;
import com.csz.assertor.sys.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Assertor
 * @since 2019-11-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
