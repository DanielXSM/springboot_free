package com.free.zdp.service.impl;

import com.free.zdp.mapper.UserMapper;
import com.free.zdp.model.User;
import com.free.zdp.service.UserService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdp123
 * @since 2019-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public String show(String name) {
        userMapper.selectByName(name);
        return null;
    }
}
