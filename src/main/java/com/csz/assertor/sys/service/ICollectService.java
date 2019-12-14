package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.entity.Collect;

import java.sql.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-12-14
 */
public interface ICollectService extends IService<Collect> {
    Integer selectOneDayCollect(Date date);
}
