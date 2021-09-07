package com.liquor.junitdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 11:57
 */
@SpringBootTest
public class Test {

    //
    @org.junit.jupiter.api.Test
    @DisplayName("测试断言equals")
    void testEquals() {
        // assertTrue与assertFalse用来判断条件是否为true或false
        assertTrue(3 < 4);
        assertFalse(4 > 5);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("测试断言NotNull")
    void testNotNull() {
        // assertNull与assertNotNull用来判断条件是否为null
        assertNotNull(new Object());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("测试断言抛异常")
    void testThrows() {
        // assertThrows用来判断执行抛出的异常是否符合预期，并可以使用异常类型接收返回值进行其他操作
        ArithmeticException arithExcep = assertThrows(ArithmeticException.class, () -> {
            int m = 5 / 0;
        });
        assertEquals("/ by zero", arithExcep.getMessage());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("测试断言超时")
    void testTimeOut() {
        // assertTimeout用来判断执行过程是否超时
        String actualResult = assertTimeout(ofSeconds(2), () -> {
            Thread.sleep(1000);
            return "a result";
        });
        System.out.println(actualResult);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("测试组合断言")
    void testAll() {
        // assertAll是组合断言，当它内部所有断言正确执行完才算通过
        assertAll("测试item商品下单",
                () -> {
                    //模拟用户余额扣减
                    assertTrue(1 < 2, "余额不足");
                },
                () -> {
                    //模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    //模拟交易流水落库
                    assertNotNull(new Object());
                }
        );
    }

    /**
     * 在许多场景中我们需要对同一个接口方法进行重复测试，例如对幂等性接口的测试。
     * JUnit Jupiter通过使用@RepeatedTest(n)指定需要重复的次数
     */
    @RepeatedTest(3)
    @DisplayName("重复测试")
    void repeatedTest() {
        System.out.println("调用");
    }

    /**
     * 参数化测试可以按照多个参数分别运行多次单元测试这里有点类似于重复性测试，只不过
     * 每次运行传入的参数不用。需要使用到@ParameterizedTest，同时也需要@ValueSource
     * 提供一组数据，它支持八种基本类型以及String和自定义对象类型，使用极其方便。
     *
     * @param a
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("参数化测试")
    void paramTest(int a) {
        assertTrue(a > 0 && a < 4);
    }
}
