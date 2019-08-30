package com.free.zdp.service;

public interface AdminService {
    String sayHello();

    /**
     * 查询爱好
     * @return
     */
    String like();

    String welcome(String name);
}
