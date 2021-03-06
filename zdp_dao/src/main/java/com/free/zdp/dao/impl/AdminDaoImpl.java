package com.free.zdp.dao.impl;

import com.free.zdp.dao.AdminDao;
import com.free.zdp.domain.TLDUSER;
import com.free.zdp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class AdminDaoImpl implements AdminDao {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public String queryUserNameById(Long id) {
        Iterable<TLDUSER> all = adminRepository.findAll();
        System.out.println("aaaaa");
        Optional<TLDUSER> byId = adminRepository.findById(id);
        TLDUSER user = byId.get();

        TLDUSER s = adminRepository.findByIds(id);
        String hello_world="开启创世乐园:----->>>>>>>>>>>>>>>>>>>>>"+s.getUsername();
        return hello_world;
    }

    /**
     * 根据名字查询爱好
     *
     * @param name
     * @return
     */
    @Override
    public String queryUserLikeByName(String name) {
        TLDUSER userByName = adminRepository.findUserByName(name);
        if(null==userByName){
            return "系统无此人信息，请联系管理员添加~~~~~";
        }
        return userByName.toString();
    }
}
