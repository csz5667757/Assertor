package com.csz.assertor.sys.service.impl;

import com.csz.assertor.sys.entity.OperatUser;
import com.csz.assertor.sys.mapper.OperatUserMapper;
import com.csz.assertor.sys.service.IOperatUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author assertor
 * @since 2019-11-26
 */
@Service
public class OperatUserServiceImpl extends ServiceImpl<OperatUserMapper, OperatUser> implements IOperatUserService {

    @Autowired
    private OperatUserMapper user;

    @Override
    public OperatUser selectByUserName(String username) {
        return user.SelectByUserName(username);
    }
}
