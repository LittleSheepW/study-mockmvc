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

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    /**
     * 不加此bean对象启动即会报错，因为controller层中依赖此bean对象
     * 这里的主要作用是：使用mock对象代替原来spring的bean，然后模拟底层数据的返回，而不是调用原本真正的实现。
     */
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
     *
     * MockMvcRequestBuilders.get()返回一个ResultActions对象。
     * ResultActions.param添加请求传值，可以带多个参数。
     * ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型。
     * ResultActions.andExpect添加执行完成后的断言，验证执行结果是否正确。
     * ResultActions.andDo添加一个MockMvcResultHandlers结果处理器，表示要对结果做点什么事情，如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * ResultActions.andReturn表示执行完成后返回相应的结果。
     *
     * @throws Exception
     */
    @Test
    public void getAuthUser() throws Exception{
        MvcResult result = mockMvc.perform(get("/mock/authUser/16"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        log.info("[getAuthUser] [result:{}]", result.getResponse().getContentAsString());
    }


}
