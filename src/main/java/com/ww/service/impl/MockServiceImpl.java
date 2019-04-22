package com.ww.service.impl;

import com.ww.pojo.AuthUser;
import com.ww.repository.MockRepository;
import com.ww.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: study-mockmvc
 * @Description: MockService业务实现层
 * @Author: Sun
 * @Create: 2019-04-22 11:18
 * @Version: 1.0
 **/
@Service
public class MockServiceImpl implements MockService {
    @Autowired
    private MockRepository mockRepository;
    @Override
    public AuthUser getAuthUser(Long id) {
        return mockRepository.findById(id).get();
    }

    @Override
    public void sout(Long id) {
        System.out.println(id);
    }
}
