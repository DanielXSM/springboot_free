package com.free.zdp.service.impl;

import com.free.zdp.dao.AdminDao;
import com.free.zdp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;



    @Override
    public String sayHello() {
        String s = adminDao.queryUserNameById(20L);
        return s;
    }

    /**
     * 查询爱好
     *
     * @return
     */
    @Override
    public String like() {
        return null;
    }

    @Override
    public String welcome(String name) {
        return adminDao.queryUserLikeByName(name);
    }
}
