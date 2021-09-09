package com.liquor.springdemo;

import org.springframework.core.Ordered;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 17:11
 */
public class B implements Ordered {
    @Override
    public int getOrder() {
        return 3;
    }
}
