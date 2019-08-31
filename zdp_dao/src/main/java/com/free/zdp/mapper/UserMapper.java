package com.free.zdp.mapper;

import com.free.zdp.model.User;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdp123
 * @since 2019-08-31
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByName(String name);
}
