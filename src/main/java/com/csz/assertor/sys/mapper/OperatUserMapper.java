package com.csz.assertor.sys.mapper;

import com.csz.assertor.sys.entity.OperatUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author assertor
 * @since 2019-11-26
 */
@Mapper
public interface OperatUserMapper extends BaseMapper<OperatUser> {
    public OperatUser SelectByUserName(String username);
}
