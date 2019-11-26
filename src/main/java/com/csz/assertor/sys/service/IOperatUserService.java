package com.csz.assertor.sys.service;

import com.csz.assertor.sys.entity.OperatUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-11-26
 */
public interface IOperatUserService extends IService<OperatUser> {
    OperatUser selectByUserName(String username);
}
