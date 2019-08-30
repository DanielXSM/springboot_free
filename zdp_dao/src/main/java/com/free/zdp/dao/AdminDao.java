package com.free.zdp.dao;

public interface AdminDao {

    String queryUserNameById(Long id);

    /**
     * 根据名字查询爱好
     * @param name
     * @return
     */
    String queryUserLikeByName(String name);
}
