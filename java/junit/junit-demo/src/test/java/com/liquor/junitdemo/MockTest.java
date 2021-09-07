package com.liquor.junitdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 13:53
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Junit5单元测试")
public class MockTest {

    /**
     * JUnit5提供了嵌套单元测试的功能，可以更好展示测试类之间的业务逻辑关系，我们通常
     * 是一个业务对应一个测试类，有业务关系的类其实可以写在一起。这样有利于进行测试。
     * 而且内联的写法可以大大减少不必要的类，精简项目，防止类爆炸等一系列问题。
     */
    @Nested
    @DisplayName("内嵌订单测试")
    class OrderTestClas {
        @Test
        @DisplayName("取消订单")
        void cancelOrder() {
            int status = -1;
            System.out.println("取消订单成功,订单状态为:" + status);
        }
    }
}
