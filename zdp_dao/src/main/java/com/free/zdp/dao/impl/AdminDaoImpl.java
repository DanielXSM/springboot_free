package com.free.zdp.dao.impl;

import com.free.zdp.dao.AdminDao;
import com.free.zdp.domain.User;
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
        Iterable<User> all = adminRepository.findAll();
        System.out.println("aaaaa");
        Optional<User> byId = adminRepository.findById(id);
        User user = byId.get();

        User s = adminRepository.findByIds(id);
        String hello_world="开启创世乐园:----->>>>>>>>>>>>>>>>>>>>>"+s.getUsername();
        return hello_world;
    }
}
