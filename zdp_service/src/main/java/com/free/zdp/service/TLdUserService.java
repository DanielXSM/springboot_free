package com.free.zdp.service;

import com.baomidou.mybatisplus.service.IService;
import com.free.zdp.model.TLdUser;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdp123
 * @since 2019-09-02
 */
public interface TLdUserService extends IService<TLdUser> {

    String queryMsgByName(String name);
}
