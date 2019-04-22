package com.ww.service;

import com.ww.pojo.AuthUser;

/**
 * @Program: study-mockmvc
 * @Description: MockService业务层接口
 * @Author: Sun
 * @Create: 2019-04-22 11:18
 * @Version: 1.0
 **/
public interface MockService {
    AuthUser getAuthUser(Long id);

    void sout(Long id);
}
