package com.free.zdp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.free.zdp.model.TLdUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdp123
 * @since 2019-09-02
 */
public interface TLdUserMapper extends BaseMapper<TLdUser> {

    TLdUser queryUserByName(String name);
}
