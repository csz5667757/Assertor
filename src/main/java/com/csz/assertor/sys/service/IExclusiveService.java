package com.csz.assertor.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.csz.assertor.sys.Vo.ExclusiveVO;
import com.csz.assertor.sys.entity.Exclusive;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author assertor
 * @since 2019-11-28
 */
public interface IExclusiveService extends IService<Exclusive> {
    List<ExclusiveVO> selectExclusive();
}
