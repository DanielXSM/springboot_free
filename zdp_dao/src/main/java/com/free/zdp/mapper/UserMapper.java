package com.free.zdp.mapper;

import com.free.zdp.model.User;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdp123
 * @since 2019-08-31
 */
public interface UserMapper extends BaseMapper<User> {

    void selectByName(String name);
}
