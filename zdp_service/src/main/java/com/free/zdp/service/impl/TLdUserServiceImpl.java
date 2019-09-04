package com.free.zdp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.free.zdp.mapper.TLdUserMapper;
import com.free.zdp.model.TLdUser;
import com.free.zdp.service.TLdUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdp123
 * @since 2019-09-02
 */
@Service
public class TLdUserServiceImpl extends ServiceImpl<TLdUserMapper, TLdUser> implements TLdUserService {
    @Resource
    private TLdUserMapper tLdUserMapper;
    @Override
    public String queryMsgByName(String name) {
        TLdUser tLdUser = tLdUserMapper.selectById(25);
        System.out.println(tLdUser.toString());
        TLdUser tLdUser1 = tLdUserMapper.queryUserByName(name);
        System.out.println(tLdUser1.toString());
        return tLdUser.toString();
    }
}
