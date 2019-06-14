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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

/**
 * 测试MockController，学习使用MockMVC模拟测试
 *
 * <b>problem<b/>: @SpringBootTest 和 @WebMvcTest注解的区别?
 * `@WebMvcTest`：
 * 主要用于controller层测试，只覆盖应用程序的controller层，HTTP请求和响应是Mock出来的，因此不会创建真正的连接。
 * 需要创建 MockMvc bean进行模拟接口调用，如果Controller层对Service层中的其他bean有依赖关系，那么需要使用Mock提供所需的依赖项。
 * 需要指定待测试的Controller；他也会启动一个Spring容器，但只会加载其中部分MVC相关的内容，
 * 不会加载的bean：Component、Service或者Repository等注解的Bean。
 * 会加载的bean：Controller/ControllerAdvice/JsonComponent/Converter/Filter/WebMvcConfigurer/HandlerMethodArgumentResolver等。
 *
 * `@SpringBootTest`：用于集成测试场景，不需要指定待测试的Controller，他会告诉Spring Boot去寻找一个
 * 主配置类（例如一个带@SpringBootApplication的类），并使用它来启动Spring应用程序上下文。SpringBootTest
 * 加载完整的应用程序并注入所有可能很慢的bean。
 *
 * @author: Sun
 * @create: 2019-04-22 11:35
 * @version: 1.0
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
     * 执行流程:
     * MockMvcRequestBuilders.get() 返回一个MockHttpServletRequestBuilder对象;
     * MockHttpServletRequestBuilder.param() 添加请求传值，可以带多个参数; (这里并没有使用到)
     * MockHttpServletRequestBuilder.accept(MediaType.TEXT_HTML_VALUE)) 设置返回类型; (这里并没有使用到)
     *
     * mockMvc.perform()方法，执行一个RequestBuilder请求，调用controller的业务处理逻辑，perform()方法返回ResultActions，返回操作结果，通过ResultActions，提供了统一的验证方式;
     *
     * ResultActions.andExpect添加执行完成后的断言，验证执行结果是否正确;
     * ResultActions.andDo添加一个MockMvcResultHandlers结果处理器，表示要对结果做点什么事情，如此处使用MockMvcResultHandlers.print()输出整个响应结果信息;
     * ResultActions.andReturn表示执行完成后返回相应的结果。
     *
     * @throws Exception
     */
    @Test
    public void getAuthUser() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mock/authUser/16"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info("[getAuthUser] [result:{}]", result.getResponse().getContentAsString());
    }


}
