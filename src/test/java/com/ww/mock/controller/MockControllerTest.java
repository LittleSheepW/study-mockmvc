package com.ww.mock.controller;

import com.ww.controller.MockController;
import com.ww.pojo.AuthUser;
import com.ww.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Program: study-mockmvc
 * @Description: 测试MockController，学习使用MockMVC模拟测试
 * @Author: Sun
 * @Create: 2019-04-22 11:35
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(MockController.class)
@Slf4j
public class MockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockService mockService;

    /**
     * 前置方法
     */
    @Before
    public void setUp() {
        AuthUser authUser = new AuthUser();
        authUser.setId(1L);
        authUser.setAccount("666");
        authUser.setName("sun");
        authUser.setPwd("888");

        // given方法，调用特定方法时返回特定值时使用
        given(mockService.getAuthUser(any())).willReturn(authUser);
    }

    /**
     * 测试MockMVC是否只走controller层，不走下层。
     * 答案是对的，我在controller层中调用了service层中一个sout方法进行打印，但是控制台中并没有输出那句话。
     * @throws Exception
     */
    @Test
    public void getAuthUser() throws Exception{
        MvcResult result = mockMvc.perform(get("/mock/authUser/16"))
                .andExpect(status().isOk())
                .andReturn();
        log.info("[getAuthUser] [result:{}]", result.getResponse().getContentAsString());
    }


}
