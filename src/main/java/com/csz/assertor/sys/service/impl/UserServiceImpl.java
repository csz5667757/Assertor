package com.csz.assertor.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.csz.assertor.sys.Vo.UserVO;
import com.csz.assertor.sys.entity.User;
import com.csz.assertor.sys.mapper.UserMapper;
import com.csz.assertor.sys.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-11-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<UserVO> getUser(Page<UserVO> page) {
        return page.setRecords(this.baseMapper.getUser(page));
    }
}
