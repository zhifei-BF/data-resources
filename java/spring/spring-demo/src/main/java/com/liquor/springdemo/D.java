package com.liquor.springdemo;

import org.springframework.core.annotation.Order;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 17:14
 */
@Order(3)
public class D {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
