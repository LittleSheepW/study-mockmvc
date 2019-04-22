package com.ww.controller;

import com.ww.pojo.AuthUser;
import com.ww.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: study-mockmvc
 * @Description: MockController控制层
 * @Author: Sun
 * @Create: 2019-04-22 11:17
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "mock")
public class MockController {

    @Autowired
    private MockService mockService;

    @GetMapping(value = "authUser/{id}")
    public AuthUser getAuthUser(@PathVariable Long id) {
        AuthUser authUser = mockService.getAuthUser(id);
        return authUser;
    }
}
