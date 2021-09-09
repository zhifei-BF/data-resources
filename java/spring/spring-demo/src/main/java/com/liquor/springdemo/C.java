package com.liquor.springdemo;

import org.springframework.core.annotation.Order;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 17:13
 */
@Order(4)
public class C {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
