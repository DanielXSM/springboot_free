package com.free.zdp.service;

import com.free.zdp.model.User;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdp123
 * @since 2019-08-31
 */
public interface UserService extends IService<User> {

    String show(String name);

    String shows(int id);
}
